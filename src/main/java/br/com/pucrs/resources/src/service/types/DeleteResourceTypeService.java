package br.com.pucrs.resources.src.service.types;

import br.com.pucrs.resources.src.repository.ResourceTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteResourceTypeService {

    private final ResourceTypeRepository resourceTypeRepository;

    public void delete(final String id) {

        if (isEmpty(id)) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }

        log.info("Deleting resource type with id: {}", id);
        resourceTypeRepository.deleteById(id);
    }
}
