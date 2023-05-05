package com.jgo.demo_graphql.infrastructure.outputport;

import java.util.List;
import java.util.UUID;

public interface EntityRepository {

  public <T> T createEntity(T entity);

  public <T> T updateEntity(T entity);

  public <T> T getEntityById(UUID id, Class<T> clazz);

  public <T> List<T> getEntityByEmail(String email, Class<T> clazz);

  public <T> T deleteEntityById(UUID id, Class<T> clazz);

  public <T> List<T> getAllEntities(Class<T> clazz);

}
