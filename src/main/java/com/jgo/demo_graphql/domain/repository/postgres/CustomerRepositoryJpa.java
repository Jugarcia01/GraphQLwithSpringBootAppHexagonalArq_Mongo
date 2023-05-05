package com.jgo.demo_graphql.domain.repository.postgres;

import com.jgo.demo_graphql.domain.model.Customer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

//-- Enable to use H2 or Postgres DB on project
// public interface CustomerRepositoryJpa extends JpaRepository<Customer, Long> {
//-- Enable only when Not use H2 or Postgres DB on project
public interface CustomerRepositoryJpa {
//--

  Optional<List<Customer>> findByEmail(String email);

  //-- Enable to use Postgres DB on project
  // Customer getOne(UUID id);
  // void deleteAllInBatch();
  //--

}
