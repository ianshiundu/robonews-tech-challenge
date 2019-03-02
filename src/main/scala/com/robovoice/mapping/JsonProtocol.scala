package com.robovoice.mapping

import com.robovoice.messages._
import de.heikoseeberger.akkahttpplayjson._
import play.api.libs.json._

trait JsonProtocol extends PlayJsonSupport {
  implicit val urlFormat: OFormat[ImgurApiRequest] = Json.format[ImgurApiRequest]
  implicit val imgurErrorFormat: OFormat[ErrorResponse] = Json.format[ErrorResponse]
  implicit val imgurErrorResponseFormat: OFormat[ImgurErrorResponse] = Json.format[ImgurErrorResponse]

  implicit val jobFormat: OFormat[JobResponseMessage] = Json.format[JobResponseMessage]
  implicit val imgurPostRequestFormat: OFormat[ImgurPostRequest] = Json.format[ImgurPostRequest]
}
