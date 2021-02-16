package objektwerks

import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future}
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
    Await.ready(server)

    sys.addShutdownHook {
      server.close()
      ()
    }
    ()
  }
}