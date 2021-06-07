package br.com.pucrs.resources.src.service.reservation;

import br.com.pucrs.resources.src.domain.Reservation;
import br.com.pucrs.resources.src.domain.Resource;
import br.com.pucrs.resources.src.dto.ReserveResourceRequest;
import br.com.pucrs.resources.src.repository.ReservationRepository;
import br.com.pucrs.resources.src.repository.ResourceRepository;
import br.com.pucrs.resources.src.validator.ReserveResourceRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.commons.lang3.BooleanUtils.isTrue;
import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateReserveResourceService {

    private final ResourceRepository resourceRepository;

    private final ReservationRepository reservationRepository;

    private final ReserveResourceRequestValidator validator;

    public Reservation reserve(final String id, final ReserveResourceRequest request) {

        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("ID inválido");
        }

        validator.accept(request);

        log.info("Finding resource  with id: {}", id);
        final Optional<Resource> resource = resourceRepository.findById(id);

        if (isFalse(resource.isPresent())) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }

        log.info("Finding all resource reserved with id: {}", resource.get().getName(), request.getLessonID());
        final List<Reservation> reservations = reservationRepository.findAllByResource__id(id);

        log.info("Reservating the resource: {} to lesson: {}", resource.get().getName(), request.getLessonID());
        final Reservation reservation = new Reservation();
        reservation.setReason(request.getReason());
        reservation.setLessonID(request.getLessonID());
        reservation.setResource(resource.get());
        reservation.setDay(request.getDay());
        reservation.setSchedule(request.getSchedule());

        if (isEmpty(reservations)) {
            return reservationRepository.save(reservation);
        }

        final boolean alreadyReserved = reservations.stream()
                .filter(s -> s.getDay().isEqual(request.getDay()))
                .filter(s -> s.getSchedule().name().equals(request.getSchedule()))
                .findFirst()
                .isPresent();

        if (isTrue(alreadyReserved)) {
            throw new IllegalArgumentException("Este recurso já reservado nessa data e horário");
        }

        log.info("Reservating the resource: {} to lesson: {}", resource.get().getName(), request.getLessonID());
        return reservationRepository.save(reservation);
    }
}
