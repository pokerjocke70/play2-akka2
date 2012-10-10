package actors

import com.typesafe.config.ConfigFactory

/**
 * Trait that loads configuration
 */

trait ConfigTrait {

  lazy val config = ConfigFactory.load()

}
