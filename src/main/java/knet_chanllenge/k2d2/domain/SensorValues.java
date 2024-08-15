package knet_chanllenge.k2d2.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class SensorValues {

    // rpm
    @Schema(description = "홀 센서 rpm", example = "1.23", type = "string")
    private BigDecimal hallRpm;

    //자이로 가속도 x
    @Schema(description = "자이로 센서 가속도 x", example = "7.12", type = "string")
    private BigDecimal gyroAccelerationX;

    //자이로 가속도 y
    @Schema(description = "자이로 센서 가속도 y", example = "-2.91", type = "string")
    private BigDecimal gyroAccelerationY;

    //자이로 가속도 z
    @Schema(description = "자이로 센서 가속도 z", example = "-8.26", type = "string")
    private BigDecimal gyroAccelerationZ;

    //자이로 각속도 x
    @Schema(description = "자이로 센서 각속도 x", example = "-0.05", type = "string")
    private BigDecimal gyroRotationX;

    //자이로 각속도 y
    @Schema(description = "자이로 센서 각속도 y", example = "-0.01", type = "string")
    private BigDecimal gyroRotationY;

    //자이로 각속도 z
    @Schema(description = "자이로 센서 각속도 z", example = "-0.01", type = "string")
    private BigDecimal gyroRotationZ;

    // gps 위도
    @Schema(description = "GPS 센서 위도", example = "37.584648", type = "string")
    private BigDecimal gpsLatitude;

    // gps 경도
    @Schema(description = "GPS 센서 경도", example = "127.026603", type = "string")
    private BigDecimal gpsLongitude;

    //온도
    @Schema(description = "온도 센서", example = "12.3", type = "string")
    private final BigDecimal temperature;

    //전류
    @Schema(description = "전류 센서", example = "1.234", type = "string")
    private final BigDecimal current;

    //전압
    @Schema(description = "전압 센서", example = "12.34", type = "string")
    private final BigDecimal voltage;

    //모터 드라이버 속도
//    private final int motorSpeed;

    //모터 드라이버 방향
//    private final boolean motorDirection;
}
