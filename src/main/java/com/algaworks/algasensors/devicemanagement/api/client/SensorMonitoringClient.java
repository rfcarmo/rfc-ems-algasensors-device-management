package com.algaworks.algasensors.devicemanagement.api.client;

import com.algaworks.algasensors.devicemanagement.api.model.SensorMonitoringOutput;
import io.hypersistence.tsid.TSID;

public interface SensorMonitoringClient {

    void enableMonitoring(TSID sensorId);

    void disableMonitoring(TSID sensorId);

    SensorMonitoringOutput getDetails(TSID sensorId);
}
