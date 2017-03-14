package connectionProvider

import slick.driver.MySQLDriver

/**
  * Created by knoldus on 14/3/17.
  */
trait MySqlDBComponent extends DBComponent {

  val driver = MySQLDriver

  import driver.api._

  val db: Database=Database.forConfig("mySqlDB")

}
