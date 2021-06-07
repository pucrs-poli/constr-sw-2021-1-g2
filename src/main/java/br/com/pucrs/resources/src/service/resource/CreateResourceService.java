package br.com.pucrs.resources.src.service.resource;

import br.com.pucrs.resources.src.domain.Resource;
import br.com.pucrs.resources.src.domain.ResourceType;
import br.com.pucrs.resources.src.dto.CreateResourceRequest;
import br.com.pucrs.resources.src.repository.ResourceRepository;
import br.com.pucrs.resources.src.repository.ResourceTypeRepository;
import br.com.pucrs.resources.src.validator.CreateResourceRequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateResourceService {

    private final ResourceRepository resourceRepository;

    private final ResourceTypeRepository resourceTypeRepository;

    private final CreateResourceRequestValidator validator;

    public Resource create(final CreateResourceRequest request) {

        validator.accept(request);

        log.info("Verifying if resource type with name {} exists", request.getResourceTypeName());
        final Optional<ResourceType> resourceType = resourceTypeRepository.findByName(request.getResourceTypeName());

        if (isNull(resourceType)) {
            throw new IllegalArgumentException("Recurso type não encontrado");
        }

        final Resource resource = new Resource();
        resource.setName(request.getResourceName());
        resource.setDescription(request.getResourceDescription());
        resource.setResourceType(resourceType.get());

        log.info("Saving resource: {}", resource);
        return resourceRepository.save(resource);
    }
}
