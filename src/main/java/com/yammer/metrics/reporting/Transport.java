package com.yammer.metrics.reporting;

import com.yammer.metrics.reporting.model.DatadogCounter;
import com.yammer.metrics.reporting.model.DatadogGauge;

import java.io.IOException;

/**
 * The transport layer for pushing metrics to datadog
 */
public interface Transport {

  /**
   * Build a request context.
   * @return
   * @throws IOException
   */
  public Request prepare() throws IOException;

  /**
   * A request for batching of metrics to be pushed to datadog.
   * The call order is expected to be:
   *    init() -> one or more of addGauge, addCounter -> send()
   */
  public interface Request {

    /**
     * Initialize the request
     * @throws IOException
     */
    void init() throws IOException;

    /**
     * Add a gauge
     * @param gauge
     * @throws IOException
     */
    void addGauge(DatadogGauge gauge) throws IOException;

    /**
     * Add a counter to the request
     * @param counter
     * @throws IOException
     */
    void addCounter(DatadogCounter counter) throws IOException;

    /**
     * Send the request to datadog
     * @throws Exception
     */
    void send() throws Exception;
  }
}
