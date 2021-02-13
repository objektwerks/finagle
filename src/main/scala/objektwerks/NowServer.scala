package objektwerks

import com.twitter.finagle.Http
import com.twitter.util.Await
import com.typesafe.config.ConfigFactory

object NowServer {
  def main(args: Array[String]): Unit = {
    val conf = ConfigFactory.load("now.conf")
    val port = conf.getString("port")

    val server = Http.serve(port, NowService.newInstance)
    Await.ready( server )

    sys.addShutdownHook {
      server.close()
      ()
    }
    ()
  }
}