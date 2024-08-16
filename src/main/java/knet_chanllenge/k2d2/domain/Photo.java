package knet_chanllenge.k2d2.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Photo {
    @Id
    private Long id;

    private LocalDateTime createdAt;

    private String url;

    public static Photo from(Long id, String url, LocalDateTime createdAt) {
        return Photo.builder()
                .id(id)
                .url(url)
                .createdAt(createdAt)
                .build();
    }
}
