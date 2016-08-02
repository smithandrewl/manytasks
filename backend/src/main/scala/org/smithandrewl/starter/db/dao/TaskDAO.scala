package org.smithandrewl.starter.db.dao



import org.smithandrewl.starter.db._
import org.smithandrewl.starter.db.mapping._
import org.smithandrewl.starter.model.Task
import slick.driver.PostgresDriver
import slick.driver.PostgresDriver.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by andrew.smith on 8/2/16.
  */
object TaskDAO {

  def insertTask(userId: Int, title: String, description: String): Future[Int] = {
    db.run(tasks += Task(0, userId, title, description))
  }

  def listTasks(): Future[Seq[Task]] = {
    db.run(tasks.result)
  }


}
