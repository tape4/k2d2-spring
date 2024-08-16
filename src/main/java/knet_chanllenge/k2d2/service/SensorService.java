package knet_chanllenge.k2d2.service;

import knet_chanllenge.k2d2.domain.Photo;
import knet_chanllenge.k2d2.domain.Sensor;
import knet_chanllenge.k2d2.domain.SensorValues;
import knet_chanllenge.k2d2.repository.PhotoRepository;
import knet_chanllenge.k2d2.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final PhotoRepository photoRepository;

    @Transactional
    public Photo save(SensorValues sensorValues) {
        LocalDateTime now = sensorValues.getCreatedAt();
        Sensor sensor = saveSensor(sensorValues, now);
        Photo photo = savePhoto(sensor.getId(), sensorValues.getUrl(), now);
        return photoRepository.save(photo);
    }

    @Transactional
    public Sensor saveSensor(SensorValues sensorValues, LocalDateTime now) {
        Sensor sensor = Sensor.from(sensorValues, now);
        sensor = sensorRepository.save(sensor);
        return sensorRepository.save(sensor);
    }

    @Transactional
    public Photo savePhoto(Long id, String url, LocalDateTime now) {
        Photo photo = Photo.from(id, url, now);
        return photoRepository.save(photo);
    }
}
