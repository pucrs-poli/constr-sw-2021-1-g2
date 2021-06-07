package br.com.pucrs.resources.src.validator;

import br.com.pucrs.resources.src.dto.CreateResourceRequest;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class CreateResourceRequestValidator implements Consumer<CreateResourceRequest> {

    @Override
    public void accept(CreateResourceRequest createResourceRequest) {

        if (isNull(createResourceRequest)) {
            throw new IllegalArgumentException("Request nulo");
        }

        if (isEmpty(createResourceRequest.getResourceName())) {
            throw new IllegalArgumentException("Resource name inválido");
        }

        if (isEmpty(createResourceRequest.getResourceTypeName())) {
            throw new IllegalArgumentException("Resource type name inválido");
        }

        if (isEmpty(createResourceRequest.getResourceDescription())) {
            throw new IllegalArgumentException("Resource description inválido");
        }
    }
}

