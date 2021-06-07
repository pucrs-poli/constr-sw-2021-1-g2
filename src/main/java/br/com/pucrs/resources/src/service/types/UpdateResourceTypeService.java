package br.com.pucrs.resources.src.service.types;

import br.com.pucrs.resources.src.domain.ResourceType;
import br.com.pucrs.resources.src.dto.UpdateResourceTypeRequest;
import br.com.pucrs.resources.src.repository.ResourceTypeRepository;
import br.com.pucrs.resources.src.validator.UpdateResourceTypeRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateResourceTypeService {

    private final ResourceTypeRepository repository;

    private final UpdateResourceTypeRequestValidator UpdateResourceTypeRequestValidator;

    public ResourceType update(final String id, final UpdateResourceTypeRequest request) {

        if (isEmpty(id)) {
            throw new IllegalArgumentException("id inválido");
        }

        UpdateResourceTypeRequestValidator.accept(request);

        final Optional<ResourceType> resourceType = repository.findById(id);

        if (isNull(resourceType)) {
            throw new IllegalArgumentException("Resource type não encontrado");
        }

        final ResourceType type = new ResourceType();
        type.set_id(id);
        type.setName(request.getName());
        type.setDescription(request.getDescription());

        log.info("Updating resource type with id: {}", id);
        return repository.save(type);
    }
}
