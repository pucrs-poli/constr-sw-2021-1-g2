package br.com.pucrs.resources.src.validator;

import br.com.pucrs.resources.src.dto.CreateResourceTypeRequest;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class CreateResourceTypeRequestValidator implements Consumer<CreateResourceTypeRequest> {

    @Override
    public void accept(CreateResourceTypeRequest request) {

        if (isNull(request)) {
            throw new IllegalArgumentException("Rquest nulo");
        }

        if (isEmpty(request.getDescription())) {
            throw new IllegalArgumentException("Description inválido");
        }

        if (isEmpty(request.getName())) {
            throw new IllegalArgumentException("Name inválido");
        }
    }
}

