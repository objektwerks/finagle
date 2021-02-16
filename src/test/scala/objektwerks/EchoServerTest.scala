package objektwerks

import com.twitter.finagle.Thrift
import com.twitter.util.{Return, Throw}
import com.typesafe.config.ConfigFactory

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class EchoServerTest extends AnyFunSuite with Matchers {
  val conf = ConfigFactory.load("test.echo.conf")
  val host = conf.getString("host")
  val port = conf.getString("port")

  val client = EchoClient(host, port)
  val server = Thrift.server.serveIface(s"$host$port", new EchoServer())

  sys.addShutdownHook {
    client.close()
    server.close()
    ()
  }

  test("echo") {
    val message = client.newMessage
    client.sendMessage(message).respond {
      case Return(response) =>
        response shouldBe message
        println(s"*** EchoServer message and response: $message == $response")
      case Throw(error) => fail(error)
    }
  }
}