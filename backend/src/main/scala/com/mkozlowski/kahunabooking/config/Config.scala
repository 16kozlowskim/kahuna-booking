package com.mkozlowski.kahunabooking.config

import com.mkozlowski.kahunabooking.email.EmailConfig
import com.mkozlowski.kahunabooking.http.HttpConfig
import com.mkozlowski.kahunabooking.infrastructure.DBConfig
import com.mkozlowski.kahunabooking.passwordreset.PasswordResetConfig
import com.mkozlowski.kahunabooking.user.UserConfig

/** Maps to the `application.conf` file. Configuration for all modules of the application.
  */
case class Config(db: DBConfig, api: HttpConfig, email: EmailConfig, passwordReset: PasswordResetConfig, user: UserConfig)
