package com.mkozlowski.kahunabooking.email.sender

import cats.effect.IO
import com.mkozlowski.kahunabooking.email.EmailData

trait EmailSender {
  def apply(email: EmailData): IO[Unit]
}
