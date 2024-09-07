package knet_chanllenge.k2d2.service;

import knet_chanllenge.k2d2.domain.AccidentPoints;
import knet_chanllenge.k2d2.domain.PointsDto;
import knet_chanllenge.k2d2.repository.AccidentPointsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {

    private final AccidentPointsRepository accidentPointsRepository;

    @Transactional(readOnly = true)
    public PointsDto getAccidentPoints(LocalDateTime from) {
        List<AccidentPoints> queryResult = accidentPointsRepository.findAllByCreatedAtBetween(from.minusHours(1), from);

        return PointsDto.fromPoints(queryResult);
    }
}
