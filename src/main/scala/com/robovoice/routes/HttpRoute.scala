package com.robovoice.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.robovoice.core.ImageUploadService

import scala.concurrent.ExecutionContext.Implicits.global

class HttpRoute(serv: ImageUploadService)(implicit actmat: ActorMaterializer) {
  val service = "images"
  val version = "v1"

  val imageUploadRoute = new ImageUploadRoute(serv)

  val route: Route =
    pathPrefix(version / service) {
      imageUploadRoute.routes
    } ~
      pathPrefix("healthchecks") {
        get {
          complete("OK")
        }
      }

}
