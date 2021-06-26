name := "lorem-ipsum-server-akkahttp"
organization :="fr.janalyse"
homepage := Some(new URL("https://github.com/dacr/lorem-ipsum-server-akkahttp"))
licenses += "Apache 2" -> url(s"http://www.apache.org/licenses/LICENSE-2.0.txt")
scmInfo := Some(ScmInfo(url(s"https://github.com/dacr/lorem-ipsum-server-akkahttp.git"), s"git@github.com:dacr/lorem-ipsum-server-akkahttp.git"))

Compile / mainClass := Some("loremipsum.server.akkahttp.Main")
packageBin / mainClass := Some("loremipsum.server.akkahttp.Main")

scalaVersion := "2.13.6"
scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature")

lazy val versions = new {
  // client side dependencies
  val swaggerui        = "3.44.0"
  val bootstrap        = "4.6.0"
  val jquery           = "3.5.1"

  // server side dependencies
  val pureConfig       = "0.16.0"
  val akka             = "2.6.15"
  val akkaHttp         = "10.2.4"
  val akkaHttpJson4s   = "1.36.0"
  val json4s           = "4.0.0"
  val logback          = "1.2.3"
  val slf4j            = "1.7.31"
  val scalatest        = "3.2.9"
  val webjarsLocator   = "0.41"
  val yamusca          = "0.8.1"
  val loremIpsum       = "1.0.3"
}

// client side dependencies
libraryDependencies ++= Seq(
  "org.webjars" % "swagger-ui" % versions.swaggerui,
  "org.webjars" % "bootstrap" % versions.bootstrap,
  "org.webjars" % "jquery"    % versions.jquery,
)

// server side dependencies
libraryDependencies ++= Seq(
  "com.github.pureconfig"  %% "pureconfig"          % versions.pureConfig,
  "org.json4s"             %% "json4s-jackson"       % versions.json4s,
  "org.json4s"             %% "json4s-ext"          % versions.json4s,
  "com.typesafe.akka"      %% "akka-http"           % versions.akkaHttp,
  "com.typesafe.akka"      %% "akka-stream"         % versions.akka,
  "com.typesafe.akka"      %% "akka-slf4j"          % versions.akka,
  "com.typesafe.akka"      %% "akka-testkit"        % versions.akka % Test,
  "com.typesafe.akka"      %% "akka-stream-testkit" % versions.akka % Test,
  "com.typesafe.akka"      %% "akka-http-testkit"   % versions.akkaHttp % Test,
  "de.heikoseeberger"      %% "akka-http-json4s"    % versions.akkaHttpJson4s,
  "org.slf4j"              %  "slf4j-api"           % versions.slf4j,
  "ch.qos.logback"         %  "logback-classic"     % versions.logback,
  "org.webjars"            %  "webjars-locator"     % versions.webjarsLocator,
  "com.github.eikek"       %% "yamusca-core"        % versions.yamusca,
  "org.scalatest"          %% "scalatest"           % versions.scalatest % Test,
  "fr.janalyse"            %% "lorem-ipsum"         % versions.loremIpsum,
)
