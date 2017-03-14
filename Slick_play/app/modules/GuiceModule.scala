package modules

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import connectionProvider.{DBComponent, MySqlDBComponent, PostgresComponent}
import play.api.{Configuration, Environment}

/**
  * Created by knoldus on 14/3/17.
  */
class GuiceModule(environment: Environment,configuration: Configuration) extends AbstractModule{

  override def configure()={
    bind(classOf[DBComponent]).annotatedWith(Names.named("MySqlComponent")).to(classOf[MySqlDBComponent])
    bind(classOf[DBComponent]).annotatedWith(Names.named("PostgresComponent")).to(classOf[PostgresComponent])
  }
}
