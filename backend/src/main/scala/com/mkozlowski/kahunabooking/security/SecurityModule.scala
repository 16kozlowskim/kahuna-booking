package com.mkozlowski.kahunabooking.security

import cats.effect.IO
import com.mkozlowski.kahunabooking.passwordreset.{PasswordResetAuthToken, PasswordResetCode, PasswordResetCodeModel}
import com.mkozlowski.kahunabooking.util.BaseModule
import doobie.util.transactor.Transactor

trait SecurityModule extends BaseModule {
  lazy val apiKeyModel = new ApiKeyModel
  lazy val apiKeyService = new ApiKeyService(apiKeyModel, idGenerator, clock)
  lazy val apiKeyAuth: Auth[ApiKey] = new Auth(new ApiKeyAuthToken(apiKeyModel), xa, clock)
  lazy val passwordResetCodeAuth: Auth[PasswordResetCode] = new Auth(new PasswordResetAuthToken(passwordResetCodeModel), xa, clock)

  def passwordResetCodeModel: PasswordResetCodeModel
  def xa: Transactor[IO]
}
