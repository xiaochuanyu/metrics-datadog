package com.yammer.metrics.reporting.serializer;

import com.yammer.metrics.reporting.model.DatadogCounter;
import com.yammer.metrics.reporting.model.DatadogGauge;

import java.io.IOException;

public interface Serializer {

  /**
   * Write starting marker of the datadog time series object
   */
  public void startObject() throws IOException;

  /**
   * Append a guauge to the time series
   * @param gauge
   * @throws IOException
   */
  public void appendGauge(DatadogGauge gauge) throws IOException;

  /**
   * Append a counter to the time series
   * @param counter
   * @throws IOException
   */
  public void appendCounter(DatadogCounter counter) throws IOException;

  /**
   * Mark ending of the datadog time series object
   * @throws IOException
   */
  public void endObject() throws IOException;

  /**
   * Get datadog time series object serialized as a string
   * @return
   * @throws IOException
   */
  public String getAsString() throws IOException;
}
