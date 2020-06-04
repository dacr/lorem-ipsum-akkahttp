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

import org.slf4j.LoggerFactory
import pureconfig.ConfigSource
import pureconfig.generic.auto._

case class ApplicationConfig(
  name:String,
  code:String,
)

case class HttpConfig(
  listeningInterface:String,
  listeningPort:Int,
)

case class SiteConfig(
  prefix:Option[String],
  url:String
) {
  val cleanedPrefix = prefix.map(_.trim).filter(_.size>0)
}

case class Content(
  title:String,
  startWithLoremIpsum:Boolean,
  truncate:Boolean,
  randomize:Boolean,
  sentencesBased:Boolean,
  minWordCount:Int,
  maxWordCount:Int,
  foregroundColor:String,
  backgroundColor:String,
)

case class LoremIpsumConfig(
  application:ApplicationConfig,
  http:HttpConfig,
  site:SiteConfig,
  content:Content,
)

// ---------------------------------------------------------------------------------------------------------------------

case class ServiceConfig(
  loremIpsum:LoremIpsumConfig
)

object ServiceConfig {
  def apply():ServiceConfig = {
    val logger = LoggerFactory.getLogger("LoremIpsumServiceConfig")
    ConfigSource.default.load[ServiceConfig] match {
      case Left(issues) =>
        issues.toList.foreach{issue => logger.error(issue.toString)}
        throw new RuntimeException("Invalid application configuration\n"+issues.toList.map(_.toString).mkString("\n"))
      case Right(config) =>
        config
    }
  }
}