package org.smithandrewl.starter.filter

import com.twitter.finagle.http.{Fields, Request, Response}
import com.twitter.finagle.{Filter, Service}
import com.twitter.logging.Logger
import com.twitter.util.Future
import org.jose4j.lang.JoseException
import org.smithandrewl.starter.auth

/**
  * Verifies that a user is authorized to access the REST API that they are calling.
  */
class AuthenticationFilter()
    extends Filter[Request, Response, Request, Response] {

  val log = Logger("AuthenticationFilter")
  val nonAdminRoutes = Seq("/home")

  def apply(req: Request,
            service: Service[Request, Response]): Future[Response] = {


    log.info(s"AuthenticationFilter: handling request of type ${req.method.toString()} for ${req.path}")

    val path = req.path
    val isAuthPath = path.startsWith("/authenticate")

    // if the path is any except for /authenticate
    if (!isAuthPath) {

      log.debug("AuthenticationFilter: path is not login path, checking for JWT header")

      val isPresent = req.headerMap.contains(Fields.Authorization)
      var jwt: String = ""

      if (isPresent) {
        log.debug("AuthenticationFilter: JWT was found")
        // Get jwt from authenticate header
        jwt = req.headerMap.getOrElse(Fields.Authorization, "")
      }
      val resp = req.response

      try {
        val invalid = !(isPresent && auth.verifyJWT(jwt))

        val jwtPayload = auth.extractPayload(jwt)

        val nonAdminRoute: Boolean =
          nonAdminRoutes.exists((route: String) => path.startsWith(route))

        val accessGranted = jwtPayload.isAdmin || nonAdminRoute
        val unauthorized = !accessGranted

        if (invalid) {
          log.warning("AuthenticationFilter: JWT is not present or is invalid")
          log.warning("AuthenticationFilter: Returning HTTP 401 unauthorized")
          // if it is not present or is not signed with the correct key, return a 401 unauthorized
          resp.setStatusCode(401)
          Future(resp)

        } else if (unauthorized) {
          log.warning("AuthenticationFilter: The current user is not authorized to access this API route")
          // TODO: Log unauthorized user action

          log.warning("AuthenticationFilter: The user is not authorized for this route, returning HTTP 403 access denied")

          // if the user is not an admin but going to an admin route, return a 403 access denied
          resp.setStatusCode(403)
          Future(resp)
        } else {
          log.debug("AuthenticationFilter: User has been authorized and the request is being let through")
          service(req)
        }
      } catch {
        case (e: JoseException) => {

          log.warning("AuthenticationFilter: The JWT was invalid")
          log.warning("AuthenticationFilter: Returning HTTP 401 unauthorized")

          // TODO: Log action with invalid JWT
          // if the Authorization header is present but invalid, return a 401 unauthorized
          resp.setStatusCode(401)
          Future(resp)
        }
      }
    } else {
      log.debug("AuthenticationFilter: The user is logging in, allowing")
      service(req)
    }
  }
}
