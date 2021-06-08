package br.com.pucrs.resources.src.service.types;

import br.com.pucrs.resources.src.domain.Resource;
import br.com.pucrs.resources.src.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllResourcesByTypeService {

    private final ResourceRepository repository;

    public List<Resource> findById(final String id) {

        log.info("Finding all resources types");
        final List<Resource> resources = repository.findByResourceType__id(id);

        if (isEmpty(resources)) {
            return emptyList();
        }

        return resources;

    }
}
