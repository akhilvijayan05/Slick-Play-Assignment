package connectionProvider

import slick.jdbc.PostgresProfile

/**
  * Created by knoldus on 14/3/17.
  */
trait PostgresComponent extends DBComponent {

  val driver = PostgresProfile

  import driver.api._

  val db = Database.forConfig("myPostgresDB")

}
