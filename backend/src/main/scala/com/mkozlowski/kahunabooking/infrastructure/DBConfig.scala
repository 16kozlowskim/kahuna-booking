package com.mkozlowski.kahunabooking.infrastructure

import com.mkozlowski.kahunabooking.config.Sensitive

case class DBConfig(username: String, password: Sensitive, url: String, migrateOnStart: Boolean, driver: String, connectThreadPoolSize: Int)
