/*
 * Copyright 2020-2022 David Crosson
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

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import loremipsum.server.akkahttp.routing.{AdminRouting, AssetsRouting, HomeRouting, LoremIpsumRouting, SwaggerRouting}

/**
 * Prepare (reduce & prefix) service routes
 * @param dependencies
 */
case class ServiceRoutes(dependencies: ServiceDependencies) {
  val config = dependencies.config.loremIpsum

  private val rawRoutes: Route = List(
    LoremIpsumRouting(dependencies),
    HomeRouting(dependencies),
    AdminRouting(dependencies),
    AssetsRouting(dependencies),
    SwaggerRouting(dependencies)
  ).map(_.routes).reduce(_ ~ _)

  val routes: Route =
    config
      .site
      .cleanedPrefix
      .map { p =>
        pathPrefix(p) {
          rawRoutes
        }
      }.getOrElse(rawRoutes)
}
