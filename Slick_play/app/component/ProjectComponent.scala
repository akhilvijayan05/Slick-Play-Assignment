package component

import connectionProvider.{DBComponent, MySqlDBComponent}
import models.Project
import table.ProjectTable

import scala.concurrent.Future

/**
  * Created by knoldus on 14/3/17.
  */
class ProjectComponent extends ProjectTable with MySqlDBComponent {
  this: DBComponent =>

  import driver.api._

  def create = db.run(projectTableQuery.schema.create)

  def insert(project: Project) = db.run(projectTableQuery += project)

  def delete(empId: Int) = {
    val query = projectTableQuery.filter(x => x.emp_id === empId)
    val action = query.delete
    db.run(action)
  }

  def updateName(id: Int, name: String): Future[Int] = {
    val query = employeeTableQuery.filter(x => x.id === id)
      .map(_.name).update(name)
    db.run(query)
  }

  def insertOrUpdate(project: Project) = {
    val query = projectTableQuery.insertOrUpdate(project)
    db.run(query)

  }
  def getAll: Future[List[Project]] = {
    db.run { projectTableQuery.to[List].result}
  }
  def truncate={

    val action = projectTableQuery.delete
    db.run(action)
  }
}

object ProjectComponent