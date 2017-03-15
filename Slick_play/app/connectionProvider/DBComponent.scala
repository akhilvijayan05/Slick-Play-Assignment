package connectionProvider

import com.typesafe.config.ConfigFactory
import slick.jdbc.JdbcProfile
import slick.jdbc.{MySQLProfile, PostgresProfile}

/**
  * Created by knoldus on 14/3/17.
  */

//@Singleton
class  DBComponent {

//  val driver: JdbcProfile=if(DBtype=="Postgres") PostgresProfile else MySQLProfile
//
//  import driver.api._
//
//  val db: Database=if(DBtype=="Postgres") Database.forConfig("myPostgresDB") else Database.forConfig("mySqlDB")
//
//  def DBtype:String={
//
//    if(play.Play.application().configuration().getString("DBtype")=="PostgresComponent"){
//      s"Postgres"
//    }
//    else
//      s"MySql"
//
//  }

  val driver: JdbcProfile = if(checkDB == "mySqlDB") MySQLProfile else if (checkDB == "myPostgresDB")PostgresProfile else throw new Exception("Invalid db name")
  import driver.api._

  val db: Database = Database.forConfig(checkDB)

  def checkDB: String = ConfigFactory.load().getString("DBtype")
}
