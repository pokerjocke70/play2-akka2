package actors

import akka.actor.Actor
import com.basho.riak.client.bucket.Bucket
import com.basho.riak.client.{IRiakObject, RiakFactory}
import com.basho.riak.client.cap.{Retrier, DefaultRetrier}

/**
 *
 * Riak trait for CRUD operations on RIAK data
 */
trait RiakConnection extends ConfigTrait { self => Actor

  lazy val riakConnection = RiakFactory.httpClient()

  lazy val attempts: Retrier = DefaultRetrier.attempts(3)


  val bucketName = "jockes-bucket"

  def save(data: IRiakObject) {
    riakConnection.fetchBucket(bucketName).execute().store(data).withRetrier(attempts).execute()
  }

  def load(key: String): IRiakObject = {
    riakConnection.fetchBucket(bucketName).execute().fetch(key).withRetrier(attempts).execute()
  }

  def delete(key: String) = {
    riakConnection.fetchBucket(bucketName).execute().delete(key).withRetrier(attempts).execute()
  }

}
