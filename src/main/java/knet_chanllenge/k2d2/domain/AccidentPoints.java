package knet_chanllenge.k2d2.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AccidentPoints {

    @Id
    private Long id;

    // gps 위도
    @Column(precision = 8, scale = 6)
    private BigDecimal gpsLatitude;

    // gps 경도
    @Column(precision = 9, scale = 6)
    private BigDecimal gpsLongitude;

    private LocalDateTime createdAt;

    public static AccidentPoints from(Sensor sensor, LocalDateTime now) {
        return AccidentPoints.builder()
                .id(sensor.getId())
                .gpsLatitude(sensor.getGpsLatitude())
                .gpsLongitude(sensor.getGpsLongitude())
                .createdAt(now)
                .build();
    }

    public static AccidentPoints from(Sensor sensor) {
        return AccidentPoints.builder()
                .id(sensor.getId())
                .gpsLatitude(sensor.getGpsLatitude())
                .gpsLongitude(sensor.getGpsLongitude())
                .createdAt(sensor.getCreatedAt())
                .build();
    }
}
