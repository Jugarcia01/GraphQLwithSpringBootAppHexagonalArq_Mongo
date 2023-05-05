package com.jgo.demo_graphql.domain.repository.mongo;

import com.jgo.demo_graphql.domain.model.Customer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, UUID> {
//public interface CustomerRepository extends MongoRepository<Customer, Long> {

  Optional<List<Customer>> findByEmail(String email);

}
