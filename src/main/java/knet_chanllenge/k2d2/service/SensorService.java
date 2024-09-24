package knet_chanllenge.k2d2.service;

import knet_chanllenge.k2d2.domain.*;
import knet_chanllenge.k2d2.repository.AccidentPointsRepository;
import knet_chanllenge.k2d2.repository.PhotoRepository;
import knet_chanllenge.k2d2.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "id"));
        List<Sensor> lastTwoRows = sensorRepository.findAll(pageRequest).getContent();

        Sensor sensor = saveSensor(sensorValues, now);
        Photo photo = savePhoto(sensor.getId(), sensorValues.getUrl(), now);

        if (isAccidentOccurSelf(lastTwoRows, sensorValues, now)) {
            saveAccidentPoints(AccidentPoints.from(lastTwoRows.get(1)));
        }

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

    public Boolean isAccidentOccurSelf(List<Sensor> lastTwoRows, SensorValues nowValues, LocalDateTime now) {

        // 가져온 Row의 수가 2가 아닌 경우
        if (lastTwoRows.size() != 2) return false;

        lastTwoRows = lastTwoRows.stream()
                .sorted((a, b) -> a.getId().compareTo(b.getId()))
                .toList();

        if (Duration.between(lastTwoRows.get(0).getCreatedAt(), now).abs().toMinutes() > 1) return false;

        for (Sensor pastRow : lastTwoRows) {
            log.info("last Values: {}", pastRow);
        }
        log.info("current Values: {}", nowValues);

        RestTemplate restTemplate = new RestTemplate();
        String requestURL = "http://61.252.59.24:51821/detect/self?GPS1_long=" + lastTwoRows.get(0).getGpsLongitude() + "&GPS1_lat=" + lastTwoRows.get(0).getGpsLatitude() +
                "&GPS2_long=" + lastTwoRows.get(1).getGpsLongitude() + "&GPS2_lat=" + lastTwoRows.get(1).getGpsLatitude() + "&GPS3_long=" + nowValues.getGpsLongitude() + "&GPS3_lat=" + nowValues.getGpsLatitude() +
                "&acc2_x=" + lastTwoRows.get(1).getGyroAccelerationX() + "&gyro2_z=" + lastTwoRows.get(1).getGyroRotationZ();

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
