package br.com.pucrs.resources.src.repository;

import br.com.pucrs.resources.src.domain.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

    List<Reservation> findAllByResource__id(final String id);

    List<Reservation> findAllByLessonID(final String id);
}