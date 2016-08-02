package org.smithandrewl.starter.db.mapping


import org.smithandrewl.starter.model.{Auth, Task}
import slick.ast.ColumnOption.{AutoInc, PrimaryKey}
import slick.driver.PostgresDriver.api._
import slick.lifted.ProvenShape

/**
  * Created by andrew.smith on 8/2/16.
  */
class TaskTable(tag: Tag) extends Table[Task](tag, "task") {

  def taskId = column[Int] ("taskid", O.PrimaryKey, O.AutoInc)
  def userId = column[Int] ("userid")
  def title = column[String] ("title")
  def description = column[String]("description")

  override def * : ProvenShape[Task] = (taskId, userId, title, description) <> ((Task.apply _).tupled, Task.unapply)
}
