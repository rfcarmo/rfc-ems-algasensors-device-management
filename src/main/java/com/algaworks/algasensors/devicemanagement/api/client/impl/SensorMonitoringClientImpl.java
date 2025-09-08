package com.algaworks.algasensors.devicemanagement.api.client.impl;

import com.algaworks.algasensors.devicemanagement.api.client.SensorMonitoringClient;
import com.algaworks.algasensors.devicemanagement.api.client.SensorMonitoringClientBadGatwayException;
import io.hypersistence.tsid.TSID;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SensorMonitoringClientImpl implements SensorMonitoringClient {

    private final RestClient restClient;

    public SensorMonitoringClientImpl(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("http://localhost:8082")
                .defaultStatusHandler(HttpStatusCode::isError, ((request, response) -> {
                    throw new SensorMonitoringClientBadGatwayException();
                }))
                .build();
    }

    @Override
    public void enableMonitoring(TSID sensorId) {
        restClient.put().uri("/api/sensors/{sensorId}/monitoring/enable", sensorId).retrieve().toBodilessEntity();
    }

    @Override
    public void disableMonitoring(TSID sensorId) {
        restClient.delete().uri("/api/sensors/{sensorId}/monitoring/enable", sensorId).retrieve().toBodilessEntity();
    }
}
