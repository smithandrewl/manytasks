package org.smithandrewl.starter.finch.endpoint

import com.twitter.bijection.Bijection
import io.finch._
import org.jboss.netty.handler.codec.http.HttpHeaders.Names
import org.smithandrewl.starter.Main._
import org.smithandrewl.starter.auth
import org.smithandrewl.starter.auth.{AuthFailure, AuthSuccess, AuthenticationResult}
import org.smithandrewl.starter.db.dao.{AppEventDAO, AuthDAO}
import org.smithandrewl.starter.json.JsonCodecs
import org.smithandrewl.starter.model.Auth
import org.smithandrewl.starter.util.Routes

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import com.twitter.util.{Future => TwitterFuture}

import scala.concurrent.ExecutionContext.Implicits.global

import com.twitter.bijection.twitter_util.UtilBijections._

import io.circe.syntax._

/**
  * Created by andrew.smith on 8/4/16.
  */
object AuthEndpoints {

  /**
    * The finch endpoint handling user deletion.
    *
    * Expects a user id parameter and an authorization header.
    */
  val deleteUser:Endpoint[String] = get(Routes.DeleteUser :: int::header(Names.AUTHORIZATION)) {
    (id: Int, jwt: String) => {

      var jwtPayload = auth.extractPayload(jwt)

      val result = Bijection[Future[Int], TwitterFuture[Int]](AuthDAO.deleteUser(id))

      result.map{
        case 0 => {

          Await.result(AppEventDAO.logDeleteUser(jwtPayload.userId, false), Duration.Inf)
          val cause: Exception = new Exception("Failed to delete user")

          log.warning(cause, s"User with UID = ${jwtPayload.userId} Failed to delete user with UID = $id")

          InternalServerError(cause)
        }

        case _ => {
          Await.result(AppEventDAO.logDeleteUser(jwtPayload.userId, true), Duration.Inf)

          log.debug(s"User with UID = ${jwtPayload.userId} deleted user with UID = $id")

          Ok("")

        }
      }
    }
  }

  /**
    * The finch endpoint for listing user accounts.
    *
    * Expects an authorization header.
    *
    */
  val api: Endpoint[String] = get(Routes.ListUsers :: header( Names.AUTHORIZATION)) {

    (jwt: String) => {
      val jwtPayload = auth.extractPayload(jwt)
      val users: TwitterFuture[Seq[Auth]] = Bijection[Future[Seq[Auth]], TwitterFuture[Seq[Auth]]](AuthDAO.getUsers())

      Await.result(AppEventDAO.logAdminListUsers(jwtPayload.userId), Duration.Inf)

      log.debug(s"User with UID = ${jwtPayload.userId} listed the users")

      Ok(users.map(usrs => usrs.asJson(JsonCodecs.authSeqEncoder) .toString()))
    }
  }

  /**
    * The finch endpoint which handler user logins.
    *
    * Expects a username and password.
    */
  val authenticate: Endpoint[String] = get(Routes.Authenticate :: string :: string) {
    (username: String, hash: String) => {
      Bijection[Future[AuthenticationResult], TwitterFuture[AuthenticationResult]](AuthDAO.login(username, hash)).map {
        case AuthSuccess(jwt) => {
          val jwtPayload = auth.extractPayload(jwt)

          Await.result(AppEventDAO.logUserLogin(true), Duration.Inf)

          log.debug(s"User $username with UID = ${jwtPayload.userId} just logged in")

          Ok(jwt)
        }
        case AuthFailure => {
          Await.result(AppEventDAO.logUserLogin(false), Duration.Inf)

          log.debug("User login failed")

          Ok("No such user or incorrect password")
        }
      }
    }
  }
  /**
    * The finch endpoint for creating a user account.
    *
    * Expects a username, password, admin status and an authorization header.
    *
    */
  val createUser: Endpoint[String] = get(Routes.CreateUser :: string :: string :: boolean :: header(Names.AUTHORIZATION)) {
    (username: String, hash: String, isAdmin: Boolean, jwt: String) => {
      val jwtPayload = auth.extractPayload(jwt)
      val userId = jwtPayload.userId
      Await.ready(AppEventDAO.logAdminCreateUserEvent(userId), Duration.Inf)

      val result = Bijection[Future[Int], TwitterFuture[Int]](AuthDAO.insertUser(username, hash, isAdmin))

      result.map{
        r=> Ok("")
      }
    }
  }

  val endpoints = deleteUser :+: api :+: authenticate :+: createUser

}
