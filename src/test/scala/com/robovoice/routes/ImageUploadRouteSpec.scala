package com.robovoice.routes

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.robovoice.core.ImageUploadService
import com.robovoice.messages.{ImgurPostRequest, JobResponseMessage}
import org.scalatest.{Matchers, WordSpec}

class ImageUploadRouteSpec extends WordSpec with Matchers with ScalatestRouteTest {
  "Upload Image to Imgur" when {

    "making a POST request to upload endpoint" should {
      val imageUploadService = new ImageUploadService()
      val api = new HttpRoute(imageUploadService).route
      val download = ImgurPostRequest("https://cdn1.carbuyer.co.uk/sites/carbuyer_d7/files/styles/16x9_720/public/2018/12/skoda-scala-front.jpg?itok=xj_WPkDb")
      val imageUploadEntity = Marshal(download)
      val request = Post("/v1/images/upload")

      "return 200 response" in {
        Post("/v1/images/upload") ~> api ~> check {
          status shouldEqual StatusCodes.OK
        }
      }
    }
  }

}
