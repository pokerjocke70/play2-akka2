package utils

/**
 * Class defining all Messages that will be passed along in the application
 */

object Messages {

  sealed trait Message

  case object GetCitiesMessage extends Message

  case class GetCityMessage(id: String) extends Message

  case class ResultMessage(json: String) extends Message

  case class ProfileMessage(id: String, name: String, age: Int) extends Message

  case class GetProfileMessage(id: String) extends Message

  case object NoProfileMessage extends Message


}
