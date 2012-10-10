package actors

import akka.actor.Actor
import utils.Messages.{NoProfileMessage, ProfileMessage, GetProfileMessage}
import scala.Some

import com.codahale.jerkson.Json._
import com.basho.riak.client.builders.RiakObjectBuilder
import akka.util.{Duration, Timeout}
import com.basho.riak.client.IRiakObject


/**
 * Riak actor that loads and saves RIAK data
 */

class RiakActor extends Actor with RiakConnection {

  implicit val timeout = Timeout(Duration(5, "seconds"))

  private def loadProfile(id: String): Option[ProfileMessage] = {
    val loaded: IRiakObject = load(id)

    loaded match {
      case null => None
      case _ =>  Some(parse[ProfileMessage](loaded.getValue))
    }

  }

  def receive = {

    case GetProfileMessage(id) => {
      sender ! loadProfile(id).getOrElse(NoProfileMessage)
    }

    case pm: ProfileMessage => {
      val value: RiakObjectBuilder = RiakObjectBuilder.newBuilder(bucketName, pm.id).withValue(generate(pm).toString)
      save(value.build())

      sender ! loadProfile(pm.id).getOrElse(None)
    }

  }
}
