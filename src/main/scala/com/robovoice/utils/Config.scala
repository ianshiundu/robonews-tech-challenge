package com.robovoice.utils

import com.typesafe.config.ConfigFactory

trait Config {
  private val config = ConfigFactory.load()
  private val httpConfig = config.getConfig("http.endpoint")
  private val imgurConfig = config.getConfig("http.imgur")
  val httpInterface: String = httpConfig.getString("interface")
  val httpPort: Int = httpConfig.getInt("port")

  val imgurClientId: String = imgurConfig.getString("clientId")
  val imgurClientSecret: String = imgurConfig.getString("clientSecret")
  val imgurUrl: String = imgurConfig.getString("url")

}
