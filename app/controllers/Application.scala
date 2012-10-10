package controllers

import play.api.mvc._
import play.api.libs.concurrent.AkkaPromise

import akka.pattern.ask
import akka.util.{Timeout, Duration}
import utils.Messages.{ProfileMessage, ResultMessage, GetCitiesMessage}

/**
 * Play controller
 */
object Application extends Controller with ActorTrait {

  implicit val timeout: Timeout = Timeout(Duration(3, "seconds"))

  /**
   * Index - just delegates to index.scala.html
   *
   */
  def index = Action {

    Ok(views.html.index(""))

  }

  /**
   * Return all cities as json data
   *
   *
   */
  def cities = Action {

    // Async necessary because actor messaging is not synchronous
      Async {

        new AkkaPromise(mongoActor ? GetCitiesMessage) map {

          case ResultMessage(msg) => Ok(msg).as("application/json")

          // Our delegate returned unknown data
          case _ => InternalServerError

        }
      }

  }

  /**
   * Save a hardcoded profile and return empty
   *
   */
  def saveProfile() = Action {

    riakActor ! ProfileMessage("123", "Joakim Sundqvist", 42)

    Created
  }

}