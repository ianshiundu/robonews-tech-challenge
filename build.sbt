enablePlugins(JavaAppPackaging, AshScriptPlugin)

name := "robovoice_challenge"

version := "0.2"

scalaVersion := "2.12.8"

dockerBaseImage := "openjdk:8-jre-alpine"
packageName in Docker := "dockerised-robovoice-challenge"

lazy val akkaHttpVersion = "10.1.7"
lazy val akkaVersion    = "2.5.20"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.robovoice",
      scalaVersion    := "2.12.4"
    )),
    name := "stackoverflowbackend",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"                      % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json"           % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml"                  % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"                    % akkaVersion,
      "com.typesafe.play" %% "play-json"                      % "2.7.1",
      "com.typesafe.play" %% "play-ws-standalone-json"        % "2.0.1",
      "de.heikoseeberger" %% "akka-http-play-json"            % "1.25.2",
      "com.typesafe.play" %% "play-ahc-ws-standalone"         % "2.0.1",
      "com.typesafe.play" %% "play"                           % "2.7.0",
      "ch.qos.logback"    %  "logback-classic" % "1.2.3",

      "org.mockito"         % "mockito-all"         % "1.9.5"         % Test,
      "com.typesafe.play" %% "play-test"            % "2.7.0"         % Test,
      "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
      "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"            % "3.0.1"         % Test
    )
  )