package objektwerks

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http._
import com.twitter.util.Future

object NowClient {
  def apply(host: String, port: String): NowClient = new NowClient(host, port)
}

class NowClient(val host: String, val port: String) {
  private val service: Service[Request, Response] =  Http.newService(s"$host$port")

  def sendRequest: Future[Response] = {
    val request = Request(Method.Get, "/")
    request.host(host)
    service(request)
  }

  def close(): Unit = {
    service.close()
    ()
  }
}