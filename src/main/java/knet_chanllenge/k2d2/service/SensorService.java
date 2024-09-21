package knet_chanllenge.k2d2.service;

import knet_chanllenge.k2d2.domain.*;
import knet_chanllenge.k2d2.repository.AccidentPointsRepository;
import knet_chanllenge.k2d2.repository.PhotoRepository;
import knet_chanllenge.k2d2.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final PhotoRepository photoRepository;
    private final AccidentPointsRepository accidentPointsRepository;

    @Transactional
    public Photo save(SensorValues sensorValues) {
        LocalDateTime now = sensorValues.getCreatedAt();
        Sensor lastValues = sensorRepository.findFirstByOrderByIdDesc();
        Sensor sensor = saveSensor(sensorValues, now);
        Photo photo = savePhoto(sensor.getId(), sensorValues.getUrl(), now);

        if (isAccidentOccur(sensorValues.getUrl())) {
            saveAccidentPoints(AccidentPoints.from(sensor, now));
        }

        if (isAccidentOccurSelf(sensor, lastValues, now)) {
            saveAccidentPoints(AccidentPoints.from(sensor, now));
        }

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

    @Transactional
    public AccidentPoints saveAccidentPoints(AccidentPoints ap) {
        return accidentPointsRepository.save(ap);
    }

    public Boolean isAccidentOccurSelf(Sensor values, Sensor lastValues, LocalDateTime now) {
        if (lastValues == null || Duration.between(lastValues.getCreatedAt(), now).abs().toMinutes() > 3) return false;
        log.info("last Values: {}", lastValues);
        log.info("current Values: {}", values);

        RestTemplate restTemplate = new RestTemplate();
        String requestURL = "http://61.252.59.24:51821/detect/self?gps1long=" + lastValues.getGpsLongitude() + "&gps1lat=" + lastValues.getGpsLatitude() +
                "&gps2long=" + values.getGpsLongitude() + "&gps2lat=" + values.getGpsLatitude() + "&acc_x=" + values.getGyroAccelerationX() + "&gyro_z=" + values.getGyroRotationZ();

        log.info("requestURL: " + requestURL);
        IsAccidentOccurDto responseBody = restTemplate.getForObject(requestURL, IsAccidentOccurDto.class);
        log.info("request [{}] Response {}", requestURL, responseBody.toString());
        return responseBody.getResult();
    }

    public Boolean isAccidentOccur(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String requestURL = "http://61.252.59.24:51821/detect?url=" + url;

        log.info("request to {}", requestURL);
        IsAccidentOccurDto responseBody = restTemplate.getForObject(requestURL, IsAccidentOccurDto.class);

        log.info("request [{}] Response {}", requestURL, responseBody.toString());
        return responseBody.getResult();
    }
}
