package controllers


import play.libs.Akka.system
import akka.actor.Props
import actors.{RiakActor, MongoDbActor}

/**
 *
 * Base trait for Actors
 */

trait ActorTrait {

  lazy val mongoActor = system.actorOf(Props[MongoDbActor])

  lazy val riakActor = system.actorOf(Props[RiakActor])

}
