package br.com.pucrs.resources.src.dto;

import br.com.pucrs.resources.src.domain.enumeration.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReserveResourceRequest implements Serializable {

    private String lessonID;

    private String reason;

    private LocalDate day;

    private Schedule schedule;
}
