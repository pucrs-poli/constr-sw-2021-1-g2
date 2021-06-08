package br.com.pucrs.resources.src.service.types;

import br.com.pucrs.resources.src.domain.ResourceType;
import br.com.pucrs.resources.src.repository.ResourceTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindResourceTypeService {

    private final ResourceTypeRepository repository;

    @SneakyThrows
    public ResourceType findByID(final String id) {

        if (isNull(id)) {
            throw new IllegalArgumentException("ID do recurso nulo");
        }

        log.info("Finding resource type with id: {}", id);
        final Optional<ResourceType> resourceType = repository.findById(id);

        if (isFalse(resourceType.isPresent())) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }
        return resourceType.get();
    }
}
