name         := "lorem-ipsum-server-akkahttp"
organization := "fr.janalyse"
description  := ""

licenses += "NON-AI-APACHE2" -> url(s"https://github.com/non-ai-licenses/non-ai-licenses/blob/main/NON-AI-APACHE2")

scalaVersion := "2.13.15"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

lazy val versions = new {
  // client side dependencies
  val swaggerui = "5.17.14"
  val bootstrap = "5.3.3"
  val jquery    = "3.7.1"
  val awesome   = "6.5.2"

  // server side dependencies
  val pureConfig      = "0.17.7"
  val pekko           = "1.1.1"
  val pekkoHttp       = "1.1.0"
  val pekkoHttpJson4s = "3.0.0"
  val json4s          = "4.0.7"
  val logback         = "1.5.8"
  val slf4j           = "2.0.16"
  val scalatest       = "3.2.19"
  val webjarsLocator  = "0.52"
  val loremIpsum      = "1.0.7"
}

// client side dependencies
libraryDependencies ++= Seq(
  "org.webjars" % "swagger-ui"   % versions.swaggerui,
  "org.webjars" % "bootstrap"    % versions.bootstrap,
  "org.webjars" % "jquery"       % versions.jquery,
  "org.webjars" % "font-awesome" % versions.awesome
)

// server side dependencies
libraryDependencies ++= Seq(
  "com.github.pureconfig" %% "pureconfig"           % versions.pureConfig,
  "org.json4s"            %% "json4s-jackson"       % versions.json4s,
  "org.json4s"            %% "json4s-ext"           % versions.json4s,
  "org.apache.pekko"      %% "pekko-http"           % versions.pekkoHttp,
  "org.apache.pekko"      %% "pekko-stream"         % versions.pekko,
  "org.apache.pekko"      %% "pekko-slf4j"          % versions.pekko,
  "org.apache.pekko"      %% "pekko-testkit"        % versions.pekko     % Test,
  "org.apache.pekko"      %% "pekko-stream-testkit" % versions.pekko     % Test,
  "org.apache.pekko"      %% "pekko-http-testkit"   % versions.pekkoHttp % Test,
  "com.github.pjfanning"  %% "pekko-http-json4s"    % versions.pekkoHttpJson4s,
  "org.slf4j"              % "slf4j-api"            % versions.slf4j,
  "ch.qos.logback"         % "logback-classic"      % versions.logback,
  "org.webjars"            % "webjars-locator"      % versions.webjarsLocator,
  "org.scalatest"         %% "scalatest"            % versions.scalatest % Test,
  "fr.janalyse"           %% "lorem-ipsum"          % versions.loremIpsum
)

Compile / mainClass    := Some("loremipsum.server.akkahttp.Main")
packageBin / mainClass := Some("loremipsum.server.akkahttp.Main")

enablePlugins(SbtTwirl)

homepage   := Some(url("https://github.com/dacr/lorem-ipsum-server-akkahttp"))
scmInfo    := Some(ScmInfo(url(s"https://github.com/dacr/lorem-ipsum-server-akkahttp.git"), s"git@github.com:dacr/lorem-ipsum-server-akkahttp.git"))
developers := List(
  Developer(
    id = "dacr",
    name = "David Crosson",
    email = "crosson.david@gmail.com",
    url = url("https://github.com/dacr")
  )
)
