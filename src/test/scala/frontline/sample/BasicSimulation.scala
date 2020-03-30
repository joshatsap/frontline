/*
 * Copyright 2011-2018 GatlingCorp (https://gatling.io)
 *
 * All rights reserved.
 */

package frontline.sample

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpConf = http
    .baseUrl("http://computer-database.gatling.io")

  val scn = scenario("scenario1").during(600)
    .exec(
      http("Page 0")
        .get("/computers?p=0")
    )
	.pause(5)
    .exec(
      http("Page 1")
        .get("/computers?p=1")
    )

  setUp(
    scn.inject(rampUsers(10) during (10 seconds))
  ).maxDuration(10 minutes).protocols(httpConf)
}
