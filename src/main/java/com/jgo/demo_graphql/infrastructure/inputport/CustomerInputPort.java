package com.jgo.demo_graphql.infrastructure.inputport;

import com.jgo.demo_graphql.application.dto.CustomerDto;
import com.jgo.demo_graphql.domain.model.Customer;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface CustomerInputPort {

  public ResponseEntity<Customer> createCustomer(CustomerDto customerDto);

  public ResponseEntity<Customer> getCustomerById(UUID id);

  public ResponseEntity<Customer> getCustomerByEmail(String email);

  public ResponseEntity<List<Customer>> getAllCustomers();

  public Boolean deleteCustomerById(UUID id);

}
