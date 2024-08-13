package knet_chanllenge.k2d2.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
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

    private boolean hall;

    //자이로 x
    @Column(precision = 10, scale = 2)
    private BigDecimal gyroscopeX;

    //자이로 y
    @Column(precision = 10, scale = 2)
    private BigDecimal gyroscopeY;

    //온도
    @Column(precision = 3, scale = 1)
    private BigDecimal temperature;

    //전류
    @Column(precision = 2, scale = 1)
    private BigDecimal current;

    //전압
    @Column(precision = 2, scale = 1)
    private BigDecimal voltage;

    //모터 드라이버 속도
    private int motorSpeed;

    //모터 드라이버 방향
    private boolean motorDirection;

    private LocalDateTime createdAt;

    public static Sensor from(SensorValues sensorValues, LocalDateTime createdAt) {
        return Sensor.builder()
                .hall(sensorValues.isHall())
                .gyroscopeX(sensorValues.getGyroscopeX())
                .gyroscopeY(sensorValues.getGyroscopeY())
                .temperature(sensorValues.getTemperature())
                .current(sensorValues.getCurrent())
                .voltage(sensorValues.getVoltage())
                .motorSpeed(sensorValues.getMotorSpeed())
                .motorDirection(sensorValues.isMotorDirection())
                .createdAt(createdAt)
                .build();
    }
}
