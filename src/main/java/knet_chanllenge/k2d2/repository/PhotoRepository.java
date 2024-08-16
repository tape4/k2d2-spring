package knet_chanllenge.k2d2.repository;

import knet_chanllenge.k2d2.domain.Photo;
import knet_chanllenge.k2d2.domain.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
