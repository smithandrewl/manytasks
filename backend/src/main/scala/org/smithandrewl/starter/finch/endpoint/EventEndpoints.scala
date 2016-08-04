package org.smithandrewl.starter.finch.endpoint


import com.twitter.bijection.Bijection
import io.finch._
import org.jboss.netty.handler.codec.http.HttpHeaders.Names
import org.smithandrewl.starter.Main._
import org.smithandrewl.starter.auth
import org.smithandrewl.starter.auth.{AuthFailure, AuthSuccess, AuthenticationResult}
import org.smithandrewl.starter.db.dao.{AppEventDAO, AuthDAO}
import org.smithandrewl.starter.json.JsonCodecs
import org.smithandrewl.starter.model.{AppEvent, Auth}
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
object EventEndpoints {

  /**
    * The finch endpoint for listing events from the event log.
    *
    * Expects an authorization header.
    */
  val listEvents: Endpoint[String] = get(Routes.ListEvents :: header(Names.AUTHORIZATION)) {

    (jwt: String) => {

      val jwtPayload = auth.extractPayload(jwt)
      val userId = jwtPayload.userId

      val events: TwitterFuture[Seq[AppEvent]] = Bijection[Future[Seq[AppEvent]], TwitterFuture[Seq[AppEvent]]](AppEventDAO.getAppEvents())

      log.debug(s"User with UID = $userId listed events")

      Ok(events.map(events => events.asJson(org.smithandrewl.starter.json.JsonCodecs.appEventSeqEncoder).toString()))
    }
  }

  /**
    * The finch endpoint for clearing the event log.
    *
    * Expects an authorization header.
    */
  val clearEvents: Endpoint[String] = get(Routes.ClearEventLog :: header(Names.AUTHORIZATION)) {
    (jwt: String) => {
      val result = Bijection[Future[Int], TwitterFuture[Int]](AppEventDAO.clearAppEvents())

      val jwtPayload = auth.extractPayload(jwt)
      val userId = jwtPayload.userId

      Await.ready(AppEventDAO.logAdminClearEventLog(userId), Duration.Inf)

      log.debug(s"User with UID = $userId cleared the event log")

      result.map((a: Int) => {
        Ok("")
      })
    }
  }

  val endpoints = listEvents :+: clearEvents
}
