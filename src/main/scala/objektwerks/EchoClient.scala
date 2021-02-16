package objektwerks

import com.twitter.finagle.Thrift
import com.twitter.util.Future

object EchoClient {
  def apply(host: String, port: String): EchoClient = new EchoClient(host, port)
}

class EchoClient(val host: String, val port: String) {
  private val methodPerEndpoint = Thrift.client.build[EchoService.MethodPerEndpoint](s"$host$port")

  def sendMessage(message: String): Future[String] = methodPerEndpoint.echo(message)

  def close(): Unit = {
    methodPerEndpoint.asClosable.close()
    ()
  }
}