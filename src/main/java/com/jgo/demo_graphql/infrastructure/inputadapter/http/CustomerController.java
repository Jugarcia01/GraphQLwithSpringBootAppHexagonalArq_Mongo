package com.jgo.demo_graphql.infrastructure.inputadapter.http;

import com.jgo.demo_graphql.application.dto.CustomerDto;
import com.jgo.demo_graphql.domain.model.Customer;
import com.jgo.demo_graphql.domain.repository.mongo.CustomerRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

  @Autowired
  private CustomerRepository customerRepository;

  @PostMapping("/addCustomer")
  public String saveCustomer(@RequestBody CustomerDto customerDto) {
    ModelMapper modelMapper = new ModelMapper();
    Customer customer = modelMapper.map(customerDto, Customer.class);
    customerRepository.save(customer);
    return "Saved customer with id: " + customer.getUuid() + " and email: " + customer.getEmail();
  }

  @GetMapping("/findCustomer/{id}")
  public Optional<Customer> getCustomer(@PathVariable UUID id) {
    return customerRepository.findById(id);
  }

  @GetMapping("/findAllCustomer")
  public List<Customer> getCustomers() {
    return customerRepository.findAll();
  }

  @DeleteMapping("/delete/{id}")
  public String deleteCustomer(@PathVariable UUID id) {
    customerRepository.deleteById(id);
    return "Deleted customer with id: " + id;
  }

}
