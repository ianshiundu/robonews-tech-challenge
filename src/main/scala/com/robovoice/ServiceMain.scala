package com.robovoice

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.robovoice.core.ImageUploadService
import com.robovoice.routes.HttpRoute
import com.robovoice.utils.Config

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object ServiceMain extends App with Config {
  val host = httpInterface
  val port = httpPort

  implicit val system: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  val log = Logging(system.eventStream, "robovoice")

  val imageService = new ImageUploadService()

  val httpRoute = new HttpRoute(imageService).route

  val bindingFuture = Http().bindAndHandle(httpRoute, httpInterface, httpPort)

  bindingFuture.map { serverBinding ⇒ log.info(s"RestApi bound to ${serverBinding.localAddress}") }.onComplete {
    case Success(_) ⇒ log.info(s"Server successfully started at {}:{}", host, port)
    case Failure(e) ⇒
      log.error(e, "Failed to bind to {}:{}!", host, port)
      system.terminate()
  }

}
