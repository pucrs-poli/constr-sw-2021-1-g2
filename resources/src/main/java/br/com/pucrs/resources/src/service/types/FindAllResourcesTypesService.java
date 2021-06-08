package br.com.pucrs.resources.src.service.types;

import br.com.pucrs.resources.src.domain.ResourceType;
import br.com.pucrs.resources.src.repository.ResourceTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllResourcesTypesService {

    private final ResourceTypeRepository repository;

    public List<ResourceType> find() {

        log.info("Finding all resources types");
        final List<ResourceType> resources = repository.findAll();

        if (isEmpty(resources)) {
            return emptyList();
        }

        return resources;
    }
}
