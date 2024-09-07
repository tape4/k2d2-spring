package knet_chanllenge.k2d2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
@AllArgsConstructor
public class PointsDto {
    private final long count;
    private final List<AccidentPoints> points;

    public static PointsDto fromPoints(List<AccidentPoints> points) {
        return PointsDto.builder()
                .count(points.size())
                .points(points)
                .build();
    }
}
