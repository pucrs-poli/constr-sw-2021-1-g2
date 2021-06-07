package br.com.pucrs.resources.src.service.reservation;

import br.com.pucrs.resources.src.domain.Reservation;
import br.com.pucrs.resources.src.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllReserveResourceByLessonIDService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> find(final String id) {

        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("ID inválido");
        }

        log.info("Finding all reserves for lesson with id: {}", id);
        final List<Reservation> reservations = reservationRepository.findAllByLessonID(id);

        if (isEmpty(reservations)) {
            return Collections.emptyList();
        }

        log.info("Finding all reserves for lesson with id: {}", id);
        return reservations;
    }
}