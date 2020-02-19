package com.ml.shortener.infraestructure.repository;

import com.ml.shortener.infraestructure.repository.domain.DomainDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceRepository extends MongoRepository<DomainDocument, String> {

  Integer getNextVal(String seqName);
}
