package com.algaworks.algasensors.devicemanagement.api.controller;

import com.algaworks.algasensors.devicemanagement.api.model.SensorInput;
import com.algaworks.algasensors.devicemanagement.api.model.SensorOutput;
import com.algaworks.algasensors.devicemanagement.common.IdGenerator;
import com.algaworks.algasensors.devicemanagement.domain.model.Sensor;
import com.algaworks.algasensors.devicemanagement.domain.model.SensorId;
import com.algaworks.algasensors.devicemanagement.domain.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorRepository sensorRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorOutput create(@RequestBody SensorInput input) {
        Sensor sensor = Sensor.builder()
                .id(new SensorId(IdGenerator.generateTSID()))
                .name(input.getName())
                .ip(input.getIp())
                .location(input.getLocation())
                .protocol(input.getProtocol())
                .model(input.getModel())
                .enabled(false)
                .build();

        sensor = sensorRepository.saveAndFlush(sensor);

        return SensorOutput.builder()
                .id(sensor.getId().getValue())
                .name(sensor.getName())
                .ip(sensor.getIp())
                .location(sensor.getLocation())
                .protocol(sensor.getProtocol())
                .model(sensor.getModel())
                .enabled(sensor.getEnabled())
                .build();
    }

}
