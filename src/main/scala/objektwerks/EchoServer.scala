package objektwerks

import com.twitter.finagle.Thrift
import com.twitter.util.{Future, Return, Throw}
import com.typesafe.config.ConfigFactory

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

    val message = client.newMessage
    client.sendMessage(message).respond {
      case Return(response) => println(s"*** EchoClient ($message) to/from EchoServer ($response)")
      case Throw(error) => println(s"Failure: ${error.getMessage}")
    }

    Thread.sleep(3000L)

    client.close()
    server.close()
    ()
  }
}