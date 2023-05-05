package com.jgo.demo_graphql.infrastructure.outputadapter.mongodb;

import com.google.common.base.Preconditions;
import com.jgo.demo_graphql.domain.repository.mongo.CustomerRepository;
import com.jgo.demo_graphql.infrastructure.outputport.EntityRepository;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component("mongodbRepository")
public class MongodbRepositoryImpl implements EntityRepository {

  @Autowired
  MongoRepository mongoRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Override
  public <T> T createEntity(T entity) {
    return (T) mongoRepository.save(entity);
  }

  @Override
  public <T> T updateEntity(T entity) {
    return (T) mongoRepository.save(entity);
  }

  @Override
  public <T> T getEntityById(UUID id, Class<T> clazz) {
    if (mongoRepository.findById(id).isPresent()) {
      return (T) mongoRepository.findById(id).get();
    }
    return null;
  }

  @Override
  public <T> List<T> getEntityByEmail(String email, Class<T> clazz) {
    if (customerRepository.findByEmail(email).isPresent()) {
      return (List<T>) customerRepository.findByEmail(email).get();
    } else {
      return Collections.emptyList();
    }
  }

  public <T> T deleteEntityById(UUID id, Class<T> clazz) {
    final T entity = (T) mongoRepository.findById(id).get();
    Preconditions.checkState(entity != null);
    mongoRepository.delete(entity);
    return entity;
  }

  @Override
  public <T> List<T> getAllEntities(Class<T> clazz) {
    return mongoRepository.findAll();
  }

}
