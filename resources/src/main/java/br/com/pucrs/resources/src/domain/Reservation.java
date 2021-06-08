package br.com.pucrs.resources.src.domain;

import br.com.pucrs.resources.src.domain.enumeration.Schedule;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@ToString(exclude = "_id")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Reservation")
@CompoundIndex(def = "{'day': 1, 'schedule': 1}")
public class Reservation implements Serializable {

    @Id
    private String _id;

    @Field(name = "lesson_id)")
    private String lessonID;

    private String reason;

    @DBRef
    private Resource resource;

    /**
     * verificar se vai ter o user
     * private String user_id;
     */

    private LocalDate day;

    private Schedule schedule;

/**
 *  controle vai ser feito pelas ambas as datas a principio.
 @Field("status") private ReservationStatus reservationStatus;
 */

}

