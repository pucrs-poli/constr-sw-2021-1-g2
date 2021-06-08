package br.com.pucrs.resources.src.validator;

import br.com.pucrs.resources.src.dto.UpdateResourceRequest;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
public class UpdateResourceRequestValidator implements Consumer<UpdateResourceRequest> {

    @Override
    public void accept(UpdateResourceRequest updateResourceRequest) {

        if (isNull(updateResourceRequest)) {
            throw new IllegalArgumentException("Objeto UpdateResourceRequest nulo");
        }

        if (isEmpty(updateResourceRequest.getResourceName())) {
            throw new IllegalArgumentException("Resource name inválido");
        }

        if (isEmpty(updateResourceRequest.getResourceTypeName())) {
            throw new IllegalArgumentException("Resource type name inválido");
        }

        if (isEmpty(updateResourceRequest.getResourceDescription())) {
            throw new IllegalArgumentException("Resource description inválido");
        }
    }
}
