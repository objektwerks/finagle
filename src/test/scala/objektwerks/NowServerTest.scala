package objektwerks

import com.twitter.finagle.Http
import com.twitter.util.{Await, Duration, Return, Throw}
import com.typesafe.config.ConfigFactory

import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class NowServerTest extends AnyFunSuite with Matchers with BeforeAndAfterAll {
  val conf = ConfigFactory.load("test.conf")
  val host = conf.getString("host")
  val port = conf.getString("port")

  val server = Await.ready( Http.serve(port, NowService.newInstance), Duration.fromSeconds(3) )
  val client = NowClient.newInstance(host, port)

  override protected def afterAll(): Unit = {
    client.close()
    server.close()
  }

  test("now") {
    val request = NowClient.newRequest(host)
    client(request).respond {
      case Return(value) => println(s"*** now result: $value ***")
      case Throw(error) => fail(error)
    }
  }
}