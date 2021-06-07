package br.com.pucrs.resources.src.service.resource;

import br.com.pucrs.resources.src.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteResourceService {

    private final ResourceRepository resourceRepository;

    public void delete(final String id) {

        if (isEmpty(id)) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }

        log.info("Deleting resource with id: {}", id);
        resourceRepository.deleteById(id);
    }
}
