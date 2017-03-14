package services

import component.DependentComponent
import models.Dependent

/**
  * Created by knoldus on 14/3/17.
  */
object Hello{

   DependentComponent.create
    val insertRes = DependentComponent.insert(Dependent(10, "akhil","friend",Some(23)))
    val insertRes1 = DependentComponent.insert(Dependent(11, "nitin","friend",Some(22)))

    val res = insertRes.map { res => s"$res row inserted" }.recover {
      case ex: Throwable => ex.getMessage
    }
    res.map(println(_))
  //  val value=DependentComponent.truncate
  //  value.map(println(_))


  Thread.sleep(10000)
}
