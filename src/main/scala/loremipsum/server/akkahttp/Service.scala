/*
 * Copyright 2020 David Crosson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package loremipsum.server.akkahttp

import akka.actor.{ActorSystem, Terminated}
import akka.http.scaladsl._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import org.slf4j.Logger

import scala.concurrent.{ExecutionContextExecutor, Future}

case class Service(dependencies: ServiceDependencies, servicesRoutes: ServiceRoutes) {
  val config = dependencies.config.loremIpsum
  val name: String = config.application.code
  val interface: String = config.http.listeningInterface
  val port: Int = config.http.listeningPort

  private val logger: Logger = org.slf4j.LoggerFactory.getLogger(name)
  logger.info(s"Service $name is starting")

  implicit val system: ActorSystem = akka.actor.ActorSystem(s"akka-http-$name-system")
  implicit val materializer: ActorMaterializer.type = akka.stream.ActorMaterializer
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(handler = servicesRoutes.routes, interface = interface, port = port)
  bindingFuture.map(_ => logger.info(s"Service $name is started"))

  def shutdown(): Future[Terminated] = {
    bindingFuture
      .flatMap(_.unbind())
      .flatMap { _ => system.terminate() }
  }
}
