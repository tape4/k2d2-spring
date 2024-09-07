package knet_chanllenge.k2d2.repository;

import knet_chanllenge.k2d2.domain.AccidentPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AccidentPointsRepository extends JpaRepository<AccidentPoints, Long> {

    @Query("SELECT e FROM AccidentPoints e WHERE e.createdAt BETWEEN :start AND :end")
    List<AccidentPoints> findAllByCreatedAtBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
