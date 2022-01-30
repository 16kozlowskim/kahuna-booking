package com.mkozlowski.kahunabooking.metrics

import cats.effect.IO
import com.mkozlowski.kahunabooking.http.Http
import com.mkozlowski.kahunabooking.infrastructure.Json._
import com.mkozlowski.kahunabooking.version.BuildInfo
import sttp.tapir.generic.auto._
import sttp.tapir.server.ServerEndpoint

/** Defines an endpoint which exposes the current application version information.
  */
class VersionApi(http: Http) {
  import VersionApi._
  import http._

  val versionEndpoint: ServerEndpoint[Any, IO] = baseEndpoint.get
    .in("version")
    .out(jsonBody[Version_OUT])
    .serverLogic { _ =>
      IO(Version_OUT(BuildInfo.lastCommitHash)).toOut
    }
}

object VersionApi {
  case class Version_OUT(buildSha: String)
}
