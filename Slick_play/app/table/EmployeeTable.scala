package table

import javax.inject.Inject

import connectionProvider.{DBComponent, MySqlDBComponent}
import models.Employee

/**
  * Created by knoldus on 14/3/17.
  */
class EmployeeTable @Inject() (dBComponent: DBComponent){

  this:DBComponent =>
  import driver.api._
  class EmployeeTable(tag: Tag) extends Table[Employee](tag, "employee") {
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    val name: Rep[String] = column[String]("name")
    val experience: Rep[Double] = column[Double]("experience")

    def * = (id, name, experience) <> (Employee.tupled, Employee.unapply)

  }

  val employeeTableQuery = TableQuery[EmployeeTable]
}
