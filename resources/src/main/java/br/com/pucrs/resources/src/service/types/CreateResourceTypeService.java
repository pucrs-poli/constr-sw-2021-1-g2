package br.com.pucrs.resources.src.service.types;

import br.com.pucrs.resources.src.domain.ResourceType;
import br.com.pucrs.resources.src.dto.CreateResourceTypeRequest;
import br.com.pucrs.resources.src.repository.ResourceTypeRepository;
import br.com.pucrs.resources.src.validator.CreateResourceTypeRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.apache.commons.lang3.BooleanUtils.isTrue;
import static org.apache.commons.lang3.StringUtils.upperCase;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateResourceTypeService {

    private final ResourceTypeRepository repository;

    private final CreateResourceTypeRequestValidator validator;

    public ResourceType create(final CreateResourceTypeRequest request) {

        validator.accept(request);

        log.info("Verifying if resource type with name {} exists", request.getName());
        final Optional<ResourceType> resourceType = repository.findByName(request.getName());

        if (isTrue(resourceType.isPresent())) {
            throw new IllegalArgumentException("Recurso type já existe");
        }

        final ResourceType type = new ResourceType();
        type.setName(upperCase(request.getName()));
        type.setDescription(request.getDescription());

        log.info("Saving resource type: {}", type);
        return repository.save(type);
    }
}
