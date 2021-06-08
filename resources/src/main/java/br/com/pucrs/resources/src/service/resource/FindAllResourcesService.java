package br.com.pucrs.resources.src.service.resource;

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
public class FindAllResourcesService {

    private final ResourceRepository repository;

    public List<Resource> find() {

        log.info("Finding all resources");
        final List<Resource> resources = repository.findAll();

        if (isEmpty(resources)) {
            return emptyList();
        }
        return resources;
    }
}
