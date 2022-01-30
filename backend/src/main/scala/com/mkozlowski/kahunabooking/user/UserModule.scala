package com.mkozlowski.kahunabooking.user

import cats.effect.IO
import com.mkozlowski.kahunabooking.email.{EmailScheduler, EmailTemplates}
import com.mkozlowski.kahunabooking.http.Http
import com.mkozlowski.kahunabooking.security.{ApiKey, ApiKeyService, Auth}
import com.mkozlowski.kahunabooking.util.BaseModule
import doobie.util.transactor.Transactor

trait UserModule extends BaseModule {
  lazy val userModel = new UserModel
  lazy val userApi = new UserApi(http, apiKeyAuth, userService, xa)
  lazy val userService = new UserService(userModel, emailScheduler, emailTemplates, apiKeyService, idGenerator, clock, config.user)

  def http: Http
  def apiKeyAuth: Auth[ApiKey]
  def emailScheduler: EmailScheduler
  def emailTemplates: EmailTemplates
  def apiKeyService: ApiKeyService
  def xa: Transactor[IO]
}
