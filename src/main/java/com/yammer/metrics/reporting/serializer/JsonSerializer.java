package com.yammer.metrics.reporting.serializer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.metrics.reporting.model.DatadogCounter;
import com.yammer.metrics.reporting.model.DatadogGauge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Serialize datadog time series object into json
 *
 * @see <a href="http://docs.datadoghq.com/api/">api docs</a>
 */
public class JsonSerializer implements Serializer {
  private static final JsonFactory jsonFactory = new JsonFactory();
  private static final ObjectMapper mapper = new ObjectMapper(jsonFactory);
  private static final Logger LOG = LoggerFactory
      .getLogger(JsonSerializer.class);

  private JsonGenerator jsonOut;
  private ByteArrayOutputStream outputStream;

  public void startObject() throws IOException {
    LOG.info("starting req");
    outputStream = new ByteArrayOutputStream();
    jsonOut = jsonFactory.createGenerator(outputStream);
    jsonOut.writeStartObject();
    jsonOut.writeFieldName("series");
    jsonOut.writeStartArray();
  }

  public void appendGauge(DatadogGauge gauge) throws IOException {
    LOG.info("adding gauge");

    mapper.writeValue(jsonOut, gauge);
  }

  public void appendCounter(DatadogCounter counter) throws IOException {
    LOG.info("adding counter");
    mapper.writeValue(jsonOut, counter);
  }

  public void endObject() throws IOException {
    LOG.info("ending req");

    jsonOut.writeEndArray();
    jsonOut.writeEndObject();
    jsonOut.flush();
    outputStream.flush();
    outputStream.close();
  }

  public String getAsString() throws IOException {
    return outputStream.toString("UTF-8");
  }


}
