package actors

import com.mongodb.casbah.{MongoConnection, MongoCollection}
import akka.actor.Actor
import com.typesafe.config.ConfigFactory


/**
 * Mongo connection trait
 */
trait MongoDbConnection extends ConfigTrait { self => Actor

  lazy val mongoConnection: MongoCollection = MongoConnection(config.getString("mongodb.host"), config.getInt("mongodb.port"))("book")("towns")

  def loadFirst(): String = {
    mongoConnection.findOne().getOrElse("{}").toString
  }

}
