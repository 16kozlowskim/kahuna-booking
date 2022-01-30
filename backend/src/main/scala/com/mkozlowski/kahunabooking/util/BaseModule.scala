package com.mkozlowski.kahunabooking.util

import com.mkozlowski.kahunabooking.config.Config

trait BaseModule {
  def idGenerator: IdGenerator
  def clock: Clock
  def config: Config
}
