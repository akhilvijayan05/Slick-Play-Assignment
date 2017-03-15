package services

import javax.inject.Inject

import component.DependentComponent
import models.Dependent

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
  * Created by knoldus on 14/3/17.
  */
class Hello @Inject() (dependentComponent:DependentComponent){

  def check(insertRes:Future[Int])={
   dependentComponent.create



    val res = insertRes.map { res => s"$res row inserted" }.recover {
      case ex: Throwable => ex.getMessage
    }
    //res.map(println(_))
  //  val value=DependentComponent.truncate
  //  value.map(println(_))
res
  }
  //Thread.sleep(10000)
}
