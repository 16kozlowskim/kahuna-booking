package com.mkozlowski.kahunabooking.metrics

import com.mkozlowski.kahunabooking.http.Http
import io.prometheus.client.CollectorRegistry

trait MetricsModule {
  lazy val metricsApi = new MetricsApi(http, collectorRegistry)
  lazy val versionApi = new VersionApi(http)
  lazy val collectorRegistry: CollectorRegistry = CollectorRegistry.defaultRegistry

  def http: Http
}
