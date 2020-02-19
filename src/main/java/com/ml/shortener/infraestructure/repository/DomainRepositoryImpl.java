package com.ml.shortener.infraestructure.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


import com.ml.shortener.infraestructure.repository.domain.DomainDocument;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

public class DomainRepositoryImpl implements DomainRepository {

  @Autowired
  private final MongoTemplate mongoTemplate;

  public DomainRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public <S extends DomainDocument> S save(S entity) {
    return this.mongoTemplate.save(entity);
  }

  @Override
  public <S extends DomainDocument> List<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  public Optional<DomainDocument> findById(String s) {
    return Optional.ofNullable(this.mongoTemplate.findOne(
        query(where("uniqueId").is(Integer.parseInt(s))),
        DomainDocument.class
    ));
  }

  @Override
  public boolean existsById(String s) {
    return false;
  }

  @Override
  public List<DomainDocument> findAll() {
    return null;
  }

  @Override
  public Iterable<DomainDocument> findAllById(Iterable<String> strings) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(String s) {

  }

  @Override
  public void delete(DomainDocument entity) {
    this.mongoTemplate.remove(entity);
  }

  @Override
  public void deleteAll(Iterable<? extends DomainDocument> entities) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public List<DomainDocument> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<DomainDocument> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public <S extends DomainDocument> S insert(S entity) {
    return null;
  }

  @Override
  public <S extends DomainDocument> List<S> insert(Iterable<S> entities) {
    return null;
  }

  @Override
  public <S extends DomainDocument> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends DomainDocument> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends DomainDocument> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends DomainDocument> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends DomainDocument> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends DomainDocument> boolean exists(Example<S> example) {
    return false;
  }
}
