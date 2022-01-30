package com.mkozlowski.kahunabooking

import cats.data.NonEmptyList
import cats.effect.IO
import com.mkozlowski.kahunabooking.email.EmailModule
import com.mkozlowski.kahunabooking.http.{Http, HttpApi}
import com.mkozlowski.kahunabooking.infrastructure.InfrastructureModule
import com.mkozlowski.kahunabooking.metrics.MetricsModule
import com.mkozlowski.kahunabooking.passwordreset.PasswordResetModule
import com.mkozlowski.kahunabooking.security.SecurityModule
import com.mkozlowski.kahunabooking.user.UserModule
import com.mkozlowski.kahunabooking.util.{Clock, DefaultClock, DefaultIdGenerator, IdGenerator, ServerEndpoints}

/** Main application module. Depends on resources initialised in [[InitModule]].
  */
trait MainModule
    extends SecurityModule
    with EmailModule
    with UserModule
    with PasswordResetModule
    with MetricsModule
    with InfrastructureModule {

  override lazy val idGenerator: IdGenerator = DefaultIdGenerator
  override lazy val clock: Clock = DefaultClock

  lazy val http: Http = new Http()

  private lazy val endpoints: ServerEndpoints = userApi.endpoints concatNel passwordResetApi.endpoints
  private lazy val adminEndpoints: ServerEndpoints = NonEmptyList.of(metricsApi.metricsEndpoint, versionApi.versionEndpoint)

  lazy val httpApi: HttpApi = new HttpApi(http, endpoints, adminEndpoints, collectorRegistry, config.api)

  lazy val startBackgroundProcesses: IO[Unit] = emailService.startProcesses().void
}
