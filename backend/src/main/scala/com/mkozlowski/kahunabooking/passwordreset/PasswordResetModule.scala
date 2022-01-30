package com.mkozlowski.kahunabooking.passwordreset

import cats.effect.IO
import com.mkozlowski.kahunabooking.email.{EmailScheduler, EmailTemplates}
import com.mkozlowski.kahunabooking.http.Http
import com.mkozlowski.kahunabooking.security.Auth
import com.mkozlowski.kahunabooking.user.UserModel
import com.mkozlowski.kahunabooking.util.BaseModule
import com.mkozlowski.kahunabooking.infrastructure.Doobie._

trait PasswordResetModule extends BaseModule {
  lazy val passwordResetCodeModel = new PasswordResetCodeModel
  lazy val passwordResetService =
    new PasswordResetService(
      userModel,
      passwordResetCodeModel,
      emailScheduler,
      emailTemplates,
      passwordResetCodeAuth,
      idGenerator,
      config.passwordReset,
      clock,
      xa
    )
  lazy val passwordResetApi = new PasswordResetApi(http, passwordResetService, xa)

  def userModel: UserModel
  def http: Http
  def passwordResetCodeAuth: Auth[PasswordResetCode]
  def emailScheduler: EmailScheduler
  def emailTemplates: EmailTemplates
  def xa: Transactor[IO]
}
