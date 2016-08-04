package org.smithandrewl.starter

import com.twitter.finagle.Http
import com.twitter.finagle.http.filter.Cors
import com.twitter.finagle.param.Stats
import com.twitter.server.TwitterServer
import com.twitter.util.{Future => TwitterFuture}
import org.jboss.netty.handler.codec.http.HttpHeaders._
import org.smithandrewl.starter.finch.endpoint.{AuthEndpoints, EventEndpoints}
import org.smithandrewl.starter.finch.filter.AuthenticationFilter


/**
  * The REST API server.
  */
object Main extends TwitterServer  {
  /**
    * The main method.
    */
  def main() {
    /**
      * Adds the CORS headers necessary for REST API calls.
      */
    val policy: Cors.Policy = Cors.Policy(
      allowsOrigin  = _ => Some("*"),
      allowsMethods = _ => Some(Seq("GET", "POST")),
      allowsHeaders = _ => Some(Seq(Names.ACCEPT, Names.AUTHORIZATION, Names.ACCESS_CONTROL_ALLOW_CREDENTIALS, Names.CONTENT_TYPE))
    )

    val endpoints     = (AuthEndpoints.endpoints :+: EventEndpoints.endpoints).toService
    val corsService = new Cors.HttpFilter(policy).andThen(new AuthenticationFilter().andThen(endpoints))
    val server      =  Http.server.configured(Stats(statsReceiver)).serve(":8080",  corsService )

    onExit { server.close() }

    com.twitter.util.Await.ready(server)
  }
}
