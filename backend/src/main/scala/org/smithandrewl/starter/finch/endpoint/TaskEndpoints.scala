package org.smithandrewl.starter.finch.endpoint

import com.twitter.bijection.Bijection
import io.finch._
import org.jboss.netty.handler.codec.http.HttpHeaders.Names
import org.smithandrewl.starter.Main._
import org.smithandrewl.starter.auth
import org.smithandrewl.starter.db.dao.{AppEventDAO, TaskDAO}
import org.smithandrewl.starter.json.JsonCodecs
import org.smithandrewl.starter.model.{Task}
import org.smithandrewl.starter.util.Routes

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import com.twitter.util.{Future => TwitterFuture}

import scala.concurrent.ExecutionContext.Implicits.global
import io.circe.syntax._
import com.twitter.bijection.twitter_util.UtilBijections._
/**
  * Created by andrew.smith on 8/4/16.
  */
object TaskEndpoints {

  val listTasks: Endpoint[String] = get(Routes.ListTasks :: header(Names.AUTHORIZATION)) {
    (jwt: String) => {
      val jwtPayload = auth.extractPayload(jwt)
      var result = Bijection[Future[Seq[Task]], TwitterFuture[Seq[Task]]](TaskDAO.listTasks())

      log.debug(s"User with UID = ${jwtPayload.userId} just listed all tasks")

      result.map {
        r => Ok(r.asJson(JsonCodecs.taskSeqEncoder).toString())
      }
    }
  }

  // TODO: If the userid  in the form submission does not match the jwt id, log it.
  val createTask: Endpoint[String] = post(Routes.CreateTask ? Endpoint.derive[Task].fromParams :: header(Names.AUTHORIZATION)) {
    (task:Task, jwt: String) => {
      var jwtPayload = auth.extractPayload(jwt)

      var result = Bijection[Future[Int], TwitterFuture[Int]](TaskDAO.insertTask(jwtPayload.userId, task.title, task.description))

      Await.result(AppEventDAO.logUserCreateTask(jwtPayload.userId), Duration.Inf)

      log.debug(s"User with UID = ${jwtPayload.userId} just created a task with title = ${task.title}")

      result.map {
        r => Ok("")
      }
    }
  }

  val endpoints = listTasks :+: createTask
}

