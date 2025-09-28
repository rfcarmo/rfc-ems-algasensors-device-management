package com.algaworks.algasensors.devicemanagement.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorDetailsOutput {

    private SensorOutput sensor;

    private SensorMonitoringOutput monitoring;

}
