package objektwerks

import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future, Return, Throw}
import com.typesafe.config.ConfigFactory

import java.time.Instant

class EchoServer extends EchoService.MethodPerEndpoint {
  override def echo(message: String): Future[String] = Future.value(message)
}

object EchoServer {
  def main(args: Array[String]): Unit = {
    val conf = ConfigFactory.load("echo.conf")
    val host = conf.getString("host")
    val port = conf.getString("port")

    val server = Thrift.server.serveIface(s"$host$port", new EchoServer())

    val client = EchoClient(host, port)
    val message = Instant.now.toString
    client.sendMessage(message).respond {
      case Return(response) => println(s"*** EchoClient ($message) to/from EchoServer ($response)")
      case Throw(error) => println(s"Failure: ${error.getMessage}")
    }

    Await.ready(server)

    sys.addShutdownHook {
      client.close()
      server.close()
      ()
    }
    ()
  }
}