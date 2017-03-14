package component

import connectionProvider.{DBComponent, MySqlDBComponent}
import models.Employee
import table.EmployeeTable

import scala.concurrent.Future

/**
  * Created by knoldus on 14/3/17.
  */

class EmployeeComponent extends EmployeeTable with MySqlDBComponent{

  this:DBComponent =>
  import driver.api._
  //val db = Database.forConfig("myPostgresDB")

  def create: Future[Unit] = db.run(employeeTableQuery.schema.create)

  def insert(emp: Employee): Future[Int] = db.run {
    employeeTableQuery += emp
  }
  def delete(exp: Double) = {
    val query = employeeTableQuery.filter(x => x.experience < exp)
    val action = query.delete
    db.run(action)
  }

  def updateName(id:Int, name:String) : Future[Int] = {
    val query = employeeTableQuery.filter(x => x.id === id)
      .map(_.name).update(name)
    db.run(query)
  }

  def updateExperience(id:Int, experience:Double) : Future[Int] = {
    val query = employeeTableQuery.filter(x => x.id === id)
      .map(_.experience).update(experience)
    db.run(query)
  }

  def insertOrUpdate(employee:Employee) = {
    val query = employeeTableQuery.insertOrUpdate(employee)
    db.run(query)
  }
  def getAll: Future[List[Employee]] = {
    db.run { employeeTableQuery.to[List].result}
  }
  def truncate={

    val action = employeeTableQuery.delete
    db.run(action)
  }
}

object EmployeeComponent