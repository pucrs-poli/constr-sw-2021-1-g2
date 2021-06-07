package br.com.pucrs.resources.src.repository;

import br.com.pucrs.resources.src.domain.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends MongoRepository<Resource, String> {

    List<Resource> findByResourceType__id(final String id);
}