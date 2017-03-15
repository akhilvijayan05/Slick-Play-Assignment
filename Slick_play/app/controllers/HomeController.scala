package controllers

import javax.inject._

import component.DependentComponent
import connectionProvider.DBComponent
import models.Dependent
import play.api._
import play.api.mvc._
import services.Hello
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() (dependentComponent: DependentComponent,hello:Hello) extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {

    Ok(views.html.index("Your new application is ready."))
  }

  def insert(emp_id:Int, name: String,relation:String,age:Option[Int])=Action.async{

    val insertRes = dependentComponent.insert(Dependent(10, "akhil","friend",Some(23)))
    //val insertRes1 = dependentComponent.insert(Dependent(11, "nitin","friend",Some(22)))
    val result=hello.check(insertRes)
    result.map(res=>Ok(views.html.index(s"$res row inserted")))
  }
}

