package br.com.pucrs.resources.src.repository;

import br.com.pucrs.resources.src.domain.ResourceType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceTypeRepository extends MongoRepository<ResourceType, String> {
    Optional<ResourceType> findByName(String resourceTypeName);
}