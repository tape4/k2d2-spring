package knet_chanllenge.k2d2.controller;

import knet_chanllenge.k2d2.domain.PointsDto;
import knet_chanllenge.k2d2.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@Slf4j
@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @GetMapping
    public ResponseEntity<PointsDto> getAccidentPoints(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime requestTime) {
        PointsDto accidentPoints = pointService.getAccidentPoints(requestTime);
        log.info(accidentPoints.toString());
        log.info("request-time : {}", requestTime);
        return ResponseEntity
                .ok()
                .body(accidentPoints);
    }
}
