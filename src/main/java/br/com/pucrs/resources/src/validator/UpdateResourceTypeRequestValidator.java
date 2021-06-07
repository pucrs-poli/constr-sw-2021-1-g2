package br.com.pucrs.resources.src.validator;

import br.com.pucrs.resources.src.dto.UpdateResourceTypeRequest;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;


@Component
public class UpdateResourceTypeRequestValidator implements Consumer<UpdateResourceTypeRequest> {

    @Override
    public void accept(UpdateResourceTypeRequest request) {

        if (isNull(request)) {
            throw new IllegalArgumentException("Objeto UpdateResourceRequest nulo");
        }

        if (isEmpty(request.getDescription())) {
            throw new IllegalArgumentException("description inválido");
        }

        if (isEmpty(request.getName())) {
            throw new IllegalArgumentException("name inválido");
        }
    }
}
