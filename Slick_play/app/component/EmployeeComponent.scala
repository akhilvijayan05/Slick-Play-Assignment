package component

import javax.inject.Inject

import connectionProvider.{DBComponent}
import models.Employee
import table.{DependentTable, EmployeeTable}

import scala.concurrent.Future

/**
  * Created by knoldus on 14/3/17.
  */

class EmployeeComponent @Inject()(val employeeTable: EmployeeTable,val dBComponent:DBComponent){

  //this:DBComponent =>
  import dBComponent.driver.api._
  //val db = Database.forConfig("myPostgresDB")

  def create: Future[Unit] = dBComponent.db.run(employeeTable.employeeTableQuery.schema.create)

  def insert(emp: Employee): Future[Int] = dBComponent.db.run(employeeTable.employeeTableQuery += emp)

  def delete(exp: Double) = {
    val query = employeeTable.employeeTableQuery.filter(x => x.experience < exp)
    val action = query.delete
    dBComponent.db.run(action)
  }

  def updateName(id:Int, name:String) : Future[Int] = {
    val query = employeeTable.employeeTableQuery.filter(x => x.id === id)
      .map(_.name).update(name)
    dBComponent.db.run(query)
  }

  def updateExperience(id:Int, experience:Double) : Future[Int] = {
    val query = employeeTable.employeeTableQuery.filter(x => x.id === id)
      .map(_.experience).update(experience)
    dBComponent.db.run(query)
  }

  def insertOrUpdate(employee:Employee) = {
    val query = employeeTable.employeeTableQuery.insertOrUpdate(employee)
    dBComponent.db.run(query)
  }
  def getAll: Future[List[Employee]] = {
    dBComponent.db.run { employeeTable.employeeTableQuery.to[List].result}
  }
  def truncate={

    val action = employeeTable.employeeTableQuery.delete
    dBComponent.db.run(action)
  }
}
