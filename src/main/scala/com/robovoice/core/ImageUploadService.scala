package com.robovoice.core

import java.io._
import java.net.URL
import java.util.UUID

import akka.stream.Materializer
import com.robovoice.mapping.JsonProtocol
import com.robovoice.messages._
import com.robovoice.utils.Config
import play.api.libs.json.JsValue
import play.api.libs.ws.DefaultBodyWritables._
import play.api.libs.ws.JsonBodyReadables._
import play.api.libs.ws.StandaloneWSClient
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.{ExecutionContext, Future}
class ImageUploadService()(implicit mat: Materializer) extends Config with JsonProtocol {
  val wsClient = StandaloneAhcWSClient()

  def downloadImage(imageUrl: String, destinationFile: String): Unit = {
    val url: URL = new URL(imageUrl)
    val is: InputStream = url.openStream()
    val os: OutputStream = new FileOutputStream(destinationFile)
    val b: Array[Byte] = Array.ofDim[Byte](2048)
    var length: Int = 0
    while ( { length = is.read(b); length != -1} ) os.write(b, 0, length)
    is.close()
    os.close()
  }

  def uploadImage(imageUrl: String, wsClient: StandaloneWSClient)(implicit ec: ExecutionContext):
  Future[Either[ImgurErrorResponse, JobResponseMessage]] = {
    val destinationFile: String = s"src/test/resources/images/upload.jpg"
    downloadImage(imageUrl, destinationFile)
    val file = new File(destinationFile)
    wsClient.url(imgurUrl).addHttpHeaders("Authorization" -> imgurClientId).post(file).map { response ⇒
      if (response.status >= 400) // application level error
        Left(response.body[JsValue].as[ImgurErrorResponse])
      else // application level good response
        Right(JobResponseMessage(UUID.randomUUID().toString))
    }
  }
}
