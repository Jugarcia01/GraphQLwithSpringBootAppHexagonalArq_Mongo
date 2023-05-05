package com.jgo.demo_graphql.application.usecase;

import com.jgo.demo_graphql.application.dto.CustomerDto;
import com.jgo.demo_graphql.domain.model.Customer;
import com.jgo.demo_graphql.infrastructure.inputport.CustomerInputPort;
import com.jgo.demo_graphql.infrastructure.outputport.EntityRepository;
import com.jgo.demo_graphql.util.EmailToUUID;
import com.jgo.demo_graphql.util.MapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class CustomerUseCase implements CustomerInputPort {

  // Before enable, Check in Customer.java have the Mongo anotations actived: @Document, @Id
  private static final String REPOSITORY = "mongodbRepository";

  // WARNING: If happen this Error: No property getOne found for type Customer!
  // Check in Customer.java have the Postgres anotations actived: @Entity, @javax.persistence.Id
  // Enable for H2 and Postgres
  // private static final String REPOSITORY = "postgresRepository";

  private EntityRepository entityRepository;

  @Autowired
  public CustomerUseCase(@Qualifier(REPOSITORY) EntityRepository entityRepository) {
    this.entityRepository = entityRepository;
  }

  @Override
  public ResponseEntity<Customer> createCustomer(CustomerDto customerDto) {
    try {
      MapperUtil mapperUtil = new MapperUtil(new ModelMapper());
      Customer customer = mapperUtil.map(customerDto, Customer.class);
      ResponseEntity<Customer> foundCustomer = getCustomerById(customer.getUuid());
      Customer emailCustomerFound = getCustomerByEmail(customer.getEmail()).getBody();

      if (!isNull(emailCustomerFound) && !emailCustomerFound.getUuid().equals(customer.getUuid())) {
        log.info("Already exist customer with id: " + emailCustomerFound.getUuid());
        throw new RuntimeException(String.format("Customer already exist, check the correct Id %s",
            emailCustomerFound.getUuid()));
      } else {
        if (isNull(foundCustomer.getBody())) {
          Customer newCustomer = Customer.builder()
              .uuid(EmailToUUID.generateUUID(customer.getEmail()))
              .email(customer.getEmail())
              .name(customer.getName())
              .lastName(customer.getLastName())
              .build();
          entityRepository.createEntity(newCustomer);
          log.info("New customer!.");
          return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        } else {
          Customer updateCustomer = Customer.builder()
              .uuid(foundCustomer.getBody().getUuid())
              .email(customer.getEmail())
              .name(customer.getName())
              .lastName(customer.getLastName())
              .build();
          entityRepository.updateEntity(updateCustomer);
          log.info("Update customer.");
          return new ResponseEntity<>(customer, HttpStatus.OK);
        }
      }
    } catch (Exception e) {
      HttpHeaders headers = new HttpHeaders();
      headers.add("ErrorMessage", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .headers(headers)
          .body(null);
    }
  }

  @Override
  public ResponseEntity<Customer> getCustomerById(UUID id) {
    try {
      Customer foundCustomer = entityRepository.getEntityById(id, Customer.class);
      if (isNull(foundCustomer)) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @Override
  public ResponseEntity<Customer> getCustomerByEmail(String email) {
    try {
      List<Customer> foundCustomer = entityRepository.getEntityByEmail(email, Customer.class);
      if (isNull(foundCustomer)) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      // TODO: Fix better answer to list, by the moment set one item!
      return new ResponseEntity<>(foundCustomer.get(0), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @Override
  public ResponseEntity<List<Customer>> getAllCustomers() {
    try {
      List<Customer> foundCustomers = entityRepository.getAllEntities(Customer.class);
      if (foundCustomers.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @Override
  public Boolean deleteCustomerById(UUID uuid) {
    try {
      entityRepository.deleteEntityById(uuid, Customer.class);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
