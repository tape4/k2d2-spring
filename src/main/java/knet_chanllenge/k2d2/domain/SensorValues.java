package knet_chanllenge.k2d2.domain;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class SensorValues {

    private final boolean hall;

    //자이로 x
    private final BigDecimal gyroscopeX;

    //자이로 y
    private final BigDecimal gyroscopeY;

    //온도
    private final BigDecimal temperature;

    //전류
    private final BigDecimal current;

    //전압
    private final BigDecimal voltage;

    //모터 드라이버 속도
    private final int motorSpeed;

    //모터 드라이버 방향
    private final boolean motorDirection;
}
