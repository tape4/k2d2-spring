package knet_chanllenge.k2d2.controller;

import knet_chanllenge.k2d2.domain.SensorValues;
import knet_chanllenge.k2d2.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody SensorValues sensorValues) {
        sensorService.save(sensorValues);
        return ResponseEntity
                .ok()
                .body(true);
    }

}
