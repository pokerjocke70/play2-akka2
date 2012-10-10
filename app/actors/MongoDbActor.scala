package actors

import akka.actor.Actor
import utils.Messages.{ResultMessage, GetCitiesMessage}

/**
 *
 * MOngodb actor that loads mongo data
 */

class MongoDbActor() extends Actor with MongoDbConnection {


  def receive = {

    case GetCitiesMessage => {
      sender ! ResultMessage(loadFirst())
    }
  }


}
