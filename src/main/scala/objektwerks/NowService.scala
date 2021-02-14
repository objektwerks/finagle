package objektwerks

import com.twitter.finagle.Service
import com.twitter.finagle.http._
import com.twitter.util.Future

import java.time.Instant

object NowService {
  def apply(): Service[Request, Response] =
    (request: Request) => Future.value {
      val response = Response(request.version, Status.Ok)
      response.contentString = Instant.now.toString
      response
    }
}