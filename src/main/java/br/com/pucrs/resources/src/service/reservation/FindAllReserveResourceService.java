package br.com.pucrs.resources.src.service.reservation;

import br.com.pucrs.resources.src.domain.Reservation;
import br.com.pucrs.resources.src.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllReserveResourceService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> findAll() {

        log.info("Finding all reservations");
        final List<Reservation> reservations = reservationRepository.findAll();

        if (isEmpty(reservations)) {
            return Collections.emptyList();
        }
        return reservations;
    }
}
