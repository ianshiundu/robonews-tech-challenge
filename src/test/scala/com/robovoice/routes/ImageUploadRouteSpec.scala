package com.robovoice.routes

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.robovoice.core.ImageUploadService
import com.robovoice.messages.ImageUploadMessage
import org.scalatest.{Matchers, WordSpec}

class ImageUploadRouteSpec extends WordSpec with Matchers with ScalatestRouteTest {
  "Upload Image to Imgur" when {

    "making a POST request to upload endpoint" should {
      val imageUploadService = new ImageUploadService()
      val api = new HttpRoute(imageUploadService).route
      val download = ImageUploadMessage(List("https://www.petmd.com/sites/default/files/Guinea-Pig-Cages-1166701066.jpg", "https://www.petmd.com/sites/default/files/Acute-Dog-Diarrhea-47066074.jpg", "https://cdn.theatlantic.com/assets/media/img/mt/2019/02/shutterstock_559266193/lead_720_405.jpg?mod=1550855715"))
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
