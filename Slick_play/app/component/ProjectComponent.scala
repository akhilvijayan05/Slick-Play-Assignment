package component

import javax.inject.Inject

import connectionProvider.{DBComponent}
import models.Project
import table.{EmployeeTable, ProjectTable}

import scala.concurrent.Future

/**
  * Created by knoldus on 14/3/17.
  */
class ProjectComponent @Inject() (val projectTable: ProjectTable,val dBComponent:DBComponent){
  //this: DBComponent =>

  import dBComponent.driver.api._

  def create = dBComponent.db.run(projectTable.projectTableQuery.schema.create)

  def insert(project: Project) = dBComponent.db.run(projectTable.projectTableQuery += project)

  def delete(empId: Int) = {
    val query = projectTable.projectTableQuery.filter(x => x.emp_id === empId)
    val action = query.delete
    dBComponent.db.run(action)
  }

  def updateName(id: Int, name: String): Future[Int] = {
    val query = projectTable.projectTableQuery.filter(x => x.emp_id === id)
      .map(_.name).update(name)
    dBComponent.db.run(query)
  }

  def insertOrUpdate(project: Project) = {
    val query = projectTable.projectTableQuery.insertOrUpdate(project)
    dBComponent.db.run(query)

  }
  def getAll: Future[List[Project]] = {
    dBComponent.db.run { projectTable.projectTableQuery.to[List].result}
  }
  def truncate={

    val action = projectTable.projectTableQuery.delete
    dBComponent.db.run(action)
  }
}
