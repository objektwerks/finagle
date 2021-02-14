package objektwerks

import com.twitter.finagle.Http
import com.twitter.util.{Return, Throw}
import com.typesafe.config.ConfigFactory

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class NowServerTest extends AnyFunSuite with Matchers {
  val conf = ConfigFactory.load("test.conf")
  val host = conf.getString("host")
  val port = conf.getString("port")

  val client = NowClient(host, port)
  val server = Http.serve(port, NowService())

  sys.addShutdownHook {
    client.close()
    server.close()
    ()
  }

  test("now") {
    client.sendRequest.respond {
      case Return(response) =>
        response.contentString.nonEmpty shouldBe true
        println(s"*** NowServer response: ${response.contentString}")
      case Throw(error) => fail(error)
    }
  }
}