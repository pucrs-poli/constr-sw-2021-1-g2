package br.com.pucrs.resources.src.service.reservation;

import br.com.pucrs.resources.src.domain.Reservation;
import br.com.pucrs.resources.src.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindReservationByIDService {

    private final ReservationRepository reservationRepository;

    public Reservation find(final String id) {

        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("ID inválido");
        }

        log.info("Finding reservation by id: {}", id);
        final Optional<Reservation> reservation = reservationRepository.findById(id);

        if (isFalse(reservation.isPresent())) {
            throw new IllegalArgumentException("Reserva não econtrada");
        }

        return reservation.get();
    }
}
