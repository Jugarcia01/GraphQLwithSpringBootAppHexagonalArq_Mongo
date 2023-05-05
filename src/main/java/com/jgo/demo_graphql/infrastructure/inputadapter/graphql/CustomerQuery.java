package com.jgo.demo_graphql.infrastructure.inputadapter.graphql;

// import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jgo.demo_graphql.domain.model.Customer;
import com.jgo.demo_graphql.domain.repository.mongo.CustomerRepository;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerQuery { //implements GraphQLQueryResolver {

  @Autowired
  private CustomerRepository customerRepository;

  public String firstCustomerQuery() {
    return "First Customer Query Executed!";
  }

  public Customer getCustomer(UUID id) {
    log.info("getCustomer in process...");
    // return customerRepository.findById(Long.valueOf(id)).get();
    return customerRepository.findById(id).get();
  }

  public List<Customer> getAllCustomers() {
    log.info("getAllCustomers in process...");
    return customerRepository.findAll();
  }

}

/* GraphiQL Queries

  query Query {
	  firstQuery
  }


  query Query {
    getCustomer(id:"1") {
      id
      email
      name
      lastName
    }
  }

  query Query {
    getAllCustomers
  }

*/

