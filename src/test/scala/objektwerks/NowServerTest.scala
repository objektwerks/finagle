package objektwerks

import com.twitter.finagle.Http
import com.twitter.util.{Await, Duration, Return, Throw}
import com.typesafe.config.ConfigFactory
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class NowServerTest extends AnyFunSuite with Matchers {
  val conf = ConfigFactory.load("test.conf")
  val host = conf.getString("host")
  val port = conf.getString("port")

  Await.ready( Http.serve(port, NowService.newInstance), Duration.fromSeconds(9) )

  test("now") {
    val client = NowClient.newInstance(host, port)
    val request = NowClient.newRequest(host)
    client(request).respond {
      case Return(value) => println(s"*** now result: $value ***")
      case Throw(error) => fail(error)
    }
  }
}