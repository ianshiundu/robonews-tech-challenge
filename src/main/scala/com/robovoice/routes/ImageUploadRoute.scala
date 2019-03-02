package com.robovoice.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import akka.stream.Materializer
import com.robovoice.core.ImageUploadService
import com.robovoice.mapping.JsonProtocol
import com.robovoice.messages.{ImgurApiRequest, ImgurPostRequest}
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.language.postfixOps

class ImageUploadRoute(imService: ImageUploadService)(implicit ec: ExecutionContext, mat: Materializer) extends JsonProtocol{
  protected val uploadImage: Route = {
    val WSClient = StandaloneAhcWSClient()
    pathPrefix("upload") {
      post {
        pathEndOrSingleSlash {
          withRequestTimeout(5 minutes) {
            entity(as[ImgurPostRequest]){ request ⇒
              complete(imService.uploadImage(request.urls, WSClient))
            }
          }
        }
      }
    }
  }

  val routes: Route = uploadImage

}
