package table

import javax.inject.Inject

import connectionProvider.{DBComponent}
import models.Dependent

/**
  * Created by knoldus on 14/3/17.
  */
class DependentTable @Inject() (val dBComponent: DBComponent)( employeeTable:EmployeeTable){

  import dBComponent.driver.api._
   class DependentTable(tag: Tag) extends Table[Dependent](tag, "dependent") {

    val emp_id = column[Int]("emp_id")
    val name = column[String]("name")
    val relation = column[String]("relation")
    val age = column[Option[Int]]("age")
    //def empForeignKey = foreignKey("emp_dependent_fk", emp_id, employeeTable.employeeTableQuery)(_.id)
    def * = (emp_id, name,relation,age) <>(Dependent.tupled, Dependent.unapply)
  }

  val dependentTableQuery = TableQuery[DependentTable]

}
