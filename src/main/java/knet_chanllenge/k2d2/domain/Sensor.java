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
@SequenceGenerator(
        name = "SENSOR_SEQ_GENERATOR",
        sequenceName = "SENSOR_SEQ",
        initialValue = 1, allocationSize = 1)
public class Sensor {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "SENSOR_SEQ_GENERATOR")
    private Long id;

    // 홀센서 rpm
    @Column(precision = 6, scale = 2)
    private BigDecimal hallRpm;

    //자이로 가속도 x
    @Column(precision = 5, scale = 3)
    private BigDecimal gyroAccelerationX;

    //자이로 가속도 y
    @Column(precision = 5, scale = 3)
    private BigDecimal gyroAccelerationY;

    //자이로 가속도 z
    @Column(precision = 5, scale = 3)
    private BigDecimal gyroAccelerationZ;

    //자이로 각속도 x
    @Column(precision = 5, scale = 3)
    private BigDecimal gyroRotationX;

    //자이로 각속도 y
    @Column(precision = 5, scale = 3)
    private BigDecimal gyroRotationY;

    //자이로 각속도 z
    @Column(precision = 5, scale = 3)
    private BigDecimal gyroRotationZ;

    // gps 위도
    @Column(precision = 8, scale = 6)
    private BigDecimal gpsLatitude;

    // gps 경도
    @Column(precision = 9, scale = 6)
    private BigDecimal gpsLongitude;

    //온도
    @Column(precision = 5, scale = 2)
    private BigDecimal temperature;

    //전류
    @Column(precision = 5, scale = 3)
    private BigDecimal current;

    //전압
    @Column(precision = 5, scale = 3)
    private BigDecimal voltage;

    //모터 드라이버 속도
    private int motorSpeed;

    //모터 드라이버 방향
    private boolean motorDirection;

    private LocalDateTime createdAt;

    public static Sensor from(SensorValues sensorValues, LocalDateTime createdAt) {
        return Sensor.builder()
                .hallRpm(sensorValues.getHallRpm())
                .gyroAccelerationX(sensorValues.getGyroAccelerationX())
                .gyroAccelerationY(sensorValues.getGyroAccelerationY())
                .gyroAccelerationZ(sensorValues.getGyroAccelerationZ())
                .gyroRotationX(sensorValues.getGyroRotationY())
                .gyroRotationY(sensorValues.getGyroRotationY())
                .gyroRotationZ(sensorValues.getGyroRotationZ())
                .gpsLatitude(sensorValues.getGpsLatitude())
                .gpsLongitude(sensorValues.getGpsLongitude())
                .temperature(sensorValues.getTemperature())
                .current(sensorValues.getCurrent())
                .voltage(sensorValues.getVoltage())
//                .motorSpeed(sensorValues.getMotorSpeed())
//                .motorDirection(sensorValues.isMotorDirection())
                .createdAt(createdAt)
                .build();
    }
}
