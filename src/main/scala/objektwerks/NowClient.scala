package objektwerks

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http._

object NowClient {
  def apply(host: String, port: String): Service[Request, Response] =  Http.newService(s"$host$port")

  def newRequest(host: String): Request = {
    val request = Request(Method.Get, "/")
    request.host(host)
    request
  }
}