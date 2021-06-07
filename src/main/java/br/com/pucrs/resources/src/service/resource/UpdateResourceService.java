package br.com.pucrs.resources.src.service.resource;

import br.com.pucrs.resources.src.domain.Resource;
import br.com.pucrs.resources.src.domain.ResourceType;
import br.com.pucrs.resources.src.dto.UpdateResourceRequest;
import br.com.pucrs.resources.src.repository.ResourceRepository;
import br.com.pucrs.resources.src.repository.ResourceTypeRepository;
import br.com.pucrs.resources.src.validator.UpdateResourceRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateResourceService {

    private final ResourceRepository resourceRepository;

    private final ResourceTypeRepository resourceTypeRepository;

    private final UpdateResourceRequestValidator validator;

    public Resource update(final String id, final UpdateResourceRequest request) {

        if (isEmpty(id)) {
            throw new IllegalArgumentException("id inválido");
        }

        validator.accept(request);

        log.info("Updating resource with name: {}", request.getResourceName());
        final Optional<Resource> resource = resourceRepository.findById(id);

        if (isFalse(resource.isPresent())) {
            throw new IllegalArgumentException("Não existe recurso com este nome");
        }

        final Optional<ResourceType> resourceType = resourceTypeRepository.findByName(request.getResourceTypeName());

        if (isFalse(resourceType.isPresent())) {
            throw new IllegalArgumentException("Não existe recurso type com este nome");
        }

        final Resource resourceUpdate = new Resource();
        resourceUpdate.set_id(id);
        resourceUpdate.setName(request.getResourceName());
        resourceUpdate.setDescription(request.getResourceDescription());
        resourceUpdate.setResourceType(resourceType.get());

        log.info("Updating resource with name: {}", request.getResourceName());
        return resourceRepository.save(resourceUpdate);
    }
}
