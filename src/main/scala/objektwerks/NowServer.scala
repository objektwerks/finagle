package objektwerks

import com.twitter.finagle.Http
import com.twitter.util.{Await, Duration}
import com.typesafe.config.ConfigFactory

object NowServer {
  val conf = ConfigFactory.load("now.conf")
  val port = conf.getString("port")

  def main(args: Array[String]): Unit = {
    val server = Await.ready( Http.serve(port, NowService.newInstance) )
    sys.addShutdownHook(
      Await.result(server.close, Duration.fromSeconds(1))
    )
  }
}