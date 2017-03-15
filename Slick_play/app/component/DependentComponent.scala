package component

import connectionProvider.{DBComponent}
import models.Dependent
import table.{DependentTable, EmployeeTable}

import scala.concurrent.Future
import javax.inject._

/**
  * Created by knoldus on 14/3/17.
  */
class DependentComponent @Inject() (val dBComponent: DBComponent,val dependentTable:DependentTable) {
  //this: DBComponent =>
  import dBComponent.driver.api._

  def create = dBComponent.db.run(dependentTable.dependentTableQuery.schema.create)

  def insert(dependent: Dependent) = {
//    val ins1=dependentTableQuery += Dependent(12,"raj","friend",Some(23))
//    val ins2=dependentTableQuery += Dependent(13,"nitin","friend",Some(22))
//    val a1=ins2.andThen(ins1).cleanUp(x=>ins1)
//    db.run(a1)
    dBComponent.db.run(dependentTable.dependentTableQuery += dependent)

  }

  def delete(empId: Int) = {
    val query = dependentTable.dependentTableQuery.filter(x => x.emp_id === empId)
    val action = query.delete
    dBComponent.db.run(action)
  }

  def updateName(id: Int, name: String): Future[Int] = {
    val query = dependentTable.dependentTableQuery.filter(x => x.emp_id === id)
      .map(_.name).update(name)
    dBComponent.db.run(query)
  }

  def updateAge(id: Int, age: Option[Int]): Future[Int] = {
    val query = dependentTable.dependentTableQuery.filter(x => x.emp_id === id)
      .map(_.age).update(age)
    dBComponent.db.run(query)
  }

  def updateRelation(id: Int, relation: String): Future[Int] = {
    val query = dependentTable.dependentTableQuery.filter(x => x.emp_id === id)
      .map(_.relation).update(relation)
    dBComponent.db.run(query)
  }

  def insertOrUpdate(dependent: Dependent) = {
    val query = dependentTable.dependentTableQuery.insertOrUpdate(dependent)
    dBComponent.db.run(query)

  }
  def getAll: Future[List[Dependent]] = {
    dBComponent.db.run { dependentTable.dependentTableQuery.to[List].result}
  }
  def truncate={

    val action = dependentTable.dependentTableQuery.delete
    dBComponent.db.run(action)
  }
//  def joinTable={
//
//    val crossJoin=for{
//      (c,s) <- dependentTable.dependentTableQuery join employeeTableQuery
//    }yield (c.name,s.name)
//  }
}
