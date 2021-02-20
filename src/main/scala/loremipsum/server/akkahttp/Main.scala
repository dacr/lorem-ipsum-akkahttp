/*
 * Copyright 2021 David Crosson
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

import org.slf4j.Logger
import ch.qos.logback.classic.util.ContextInitializer

object Main {
  System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "loremipsum/logback.xml")
  def main(args: Array[String]): Unit = {
    val logger: Logger = org.slf4j.LoggerFactory.getLogger("LoremIpsumMain")
    logger.info(s"lorem-ipsum application is starting")
    val dependencies = ServiceDependencies.defaults
    val serviceRoutes = ServiceRoutes(dependencies)
    Service(dependencies, serviceRoutes)
  }
}
