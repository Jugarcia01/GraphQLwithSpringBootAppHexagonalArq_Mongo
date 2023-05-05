package com.jgo.demo_graphql.infrastructure.inputadapter.http;

import com.jgo.demo_graphql.application.dto.CustomerDto;
import com.jgo.demo_graphql.domain.model.Customer;
import com.jgo.demo_graphql.infrastructure.inputport.CustomerInputPort;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerApi {

  private CustomerInputPort customerInputPort;

  public CustomerApi(CustomerInputPort customerInputPort) {
    this.customerInputPort = customerInputPort;
  }

  @PostMapping(value = {"add", "update"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customerDto) {
    return customerInputPort.createCustomer(customerDto);
  }

  @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Customer> getCustomer(@PathVariable UUID id) {
    return customerInputPort.getCustomerById(id);
  }

  @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Customer> getCustomer(@RequestBody String email) {
    return customerInputPort.getCustomerByEmail(email);
  }

  @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Customer>> getAllCustomers() {
    return customerInputPort.getAllCustomers();
  }

  @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> deleteCustomer(@PathVariable UUID id) {
    ResponseEntity<String> response;
    Boolean isDeleted = customerInputPort.deleteCustomerById(id);
    if (isDeleted) {
      return ResponseEntity.ok("The record with id " + id + " was deleted successfully!");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error was present or record don't exist.");
    }
  }

}
