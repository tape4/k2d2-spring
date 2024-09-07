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
        Sensor sensor = saveSensor(sensorValues, now);
        Photo photo = savePhoto(sensor.getId(), sensorValues.getUrl(), now);

        if (isAccidentOccur(sensorValues.getUrl())) {
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

    public Boolean isAccidentOccur(String url) {
        RestTemplate restTemplate = new RestTemplate();
        // 랜덤으로 세계 맥주에 대한 정보를 주는 url
        String RequestUrl = "http://localhost:5000/detect?url=" + url;

        IsAccidentOccurDto responseBody = restTemplate.getForObject(RequestUrl, IsAccidentOccurDto.class);
        return responseBody.getResult();
    }
}
