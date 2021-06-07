package br.com.pucrs.resources.src.service.resource;

import br.com.pucrs.resources.src.domain.Resource;
import br.com.pucrs.resources.src.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindResourceService {

    private final ResourceRepository repository;

    public Resource findByID(final String resourceID) {

        if (isNull(resourceID)) {
            throw new IllegalArgumentException("ID do recurso nulo");
        }

        log.info("Finding resource with id: {}", resourceID);
        final Optional<Resource> resource = repository.findById(resourceID);

        if (isFalse(resource.isPresent())) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }

        return resource.get();
    }
}
