package table

import javax.inject.Inject

import connectionProvider.{DBComponent}
import models.Project

/**
  * Created by knoldus on 14/3/17.
  */
class ProjectTable @Inject() (val dBComponent: DBComponent,employeeTable:EmployeeTable){

  //this:DBComponent =>
  import dBComponent.driver.api._
  class ProjectTable(tag: Tag) extends Table[Project](tag, "project") {
    val name = column[String]("name")
    val emp_id = column[Int]("emp_id")
    //def empForeignKey = foreignKey("emp_project_fk", emp_id, employeeTable.employeeTableQuery)(_.id)
    def * = (emp_id, name) <>(Project.tupled, Project.unapply)
  }

  val projectTableQuery = TableQuery[ProjectTable]

}
