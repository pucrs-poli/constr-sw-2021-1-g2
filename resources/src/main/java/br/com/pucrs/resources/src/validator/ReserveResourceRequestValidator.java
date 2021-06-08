package br.com.pucrs.resources.src.validator;

import br.com.pucrs.resources.src.dto.ReserveResourceRequest;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;


@Component
public class ReserveResourceRequestValidator implements Consumer<ReserveResourceRequest> {

    @Override
    public void accept(ReserveResourceRequest request) {

        if (isNull(request)) {
            throw new IllegalArgumentException("Objeto ReserveResourceRequest nulo");
        }

        if (isEmpty(request.getLessonID())) {
            throw new IllegalArgumentException("Lesson ID inválido");
        }

        if (isNull(request.getSchedule())) {
            throw new IllegalArgumentException("Schedule inválido");
        }

        if (isEmpty(request.getReason())) {
            throw new IllegalArgumentException("Reason inválido");
        }

        if (isNull(request.getDay())) {
            throw new IllegalArgumentException("Day inválido");
        }
    }
}

