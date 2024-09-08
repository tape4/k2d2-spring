package knet_chanllenge.k2d2.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IsAccidentOccurDto {
    Boolean result;
}
