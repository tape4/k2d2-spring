package knet_chanllenge.k2d2.service;

import knet_chanllenge.k2d2.domain.Sensor;
import knet_chanllenge.k2d2.domain.SensorValues;
import knet_chanllenge.k2d2.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;

    public Sensor save(SensorValues sensorValues) {
        LocalDateTime now = LocalDateTime.now();
        Sensor sensor = Sensor.from(sensorValues, now);
        return sensorRepository.save(sensor);
    }
}
