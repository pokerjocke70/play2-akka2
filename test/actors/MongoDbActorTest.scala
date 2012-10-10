package actors


import akka.actor.ActorSystem
import akka.testkit.{TestKit, TestActorRef}
import org.junit.Test
import akka.dispatch.Await
import akka.pattern.ask
import akka.util.{Timeout, Duration}

import utils.Messages.{GetCitiesMessage, ResultMessage}
import org.scalatest.junit.{ShouldMatchersForJUnit, JUnitSuite}


/**
 *
 * @author Joakim Sundqvist
 */
class MongoDbActorTest extends TestKit(ActorSystem("test")) with JUnitSuite with ShouldMatchersForJUnit {


  implicit val timeout: Timeout = Timeout(Duration(2, "seconds"))

  val actorRef = TestActorRef(new MongoDbActor() with TestMongoConnection)


  @Test
  def testReceive() {

    val result: ResultMessage = Await.result((actorRef ? GetCitiesMessage), Duration(2, "seconds")).asInstanceOf[ResultMessage]

    result.json should be(Defaults.returnJson)

  }

  object Defaults {

    val returnJson = "{test: 'test'}"
  }


  trait TestMongoConnection extends MongoConnection {

    override def loadFirst() = Defaults.returnJson
  }

}


