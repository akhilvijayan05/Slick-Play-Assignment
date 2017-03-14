package connectionProvider

import slick.driver.JdbcProfile
import slick.jdbc.{MySQLProfile, PostgresProfile}

/**
  * Created by knoldus on 14/3/17.
  */
trait  DBComponent {

  val driver: JdbcProfile=if(DBtype=="Postgres") PostgresProfile else MySQLProfile

  import driver.api._

  val db: Database=if(DBtype=="Postgres") Database.forConfig("myPostgresDB") else Database.forConfig("mySqlDB")

  def DBtype:String={

    if(play.Play.application().configuration().getString("DBtype")=="PostgresComponent"){
      s"Postgres"
    }
    else
      s"MySql"

  }

}
