package com.jgo.demo_graphql.infrastructure.inputadapter.graphql;

import com.jgo.demo_graphql.application.dto.CustomerDto;
import com.jgo.demo_graphql.domain.model.Customer;
import com.jgo.demo_graphql.infrastructure.inputport.CustomerInputPort;
import com.jgo.demo_graphql.util.EmailToUUID;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerSpqrQuery {
  /* endpoint of project:
   http://localhost:8080/graphiql

  - Example getCustomer request:
    query {
    	 customer(uuid:"1adfa52b-a57c-3b40-8da4-388526f6595b") {
      	 email
         name
         lastName
       }
    }

  - Example getCustomerByEmail request:
    query{
      customerByEmail(email:"sgonzales@mail.com") {
        uuid
        name
        lastName
      }
    }

  - Example getAllCustomers request:
    query {
    	 customers {
	        uuid
          email
          name
          lastName
	     }
    }

  - Example createCustomer request:
    mutation{
      createCustomer(
        lastName: "Garcia"
        email: "julian.garcia@example.com"
        name: "Julian")
      {
        uuid
        lastName
        email
        name
      }
    }

  - Example updateCustomer request:
    mutation{
      updateCustomer(
        uuid: "729c5d13-261a-4b46-bb11-9af04cb2cd3f"
        lastName: "Bunny"
        email: "lbunny@mail.com"
        name: "Lolita")
      {
        uuid
        lastName
        email
        name
      }
    }

  - Example delete request; before to use MODIFY AND CHECK THAT ID EXIST ON YOUR DATABASE!:
    mutation {
      delete(uuid: "1adfa52b-a57c-3b40-8da4-388526f6595d")
    }

  */

  private CustomerInputPort customerInputPort;

  public CustomerSpqrQuery(CustomerInputPort customerInputPort) {
    this.customerInputPort = customerInputPort;
  }

  /**
   * Create or update a Customer based on data given.
   * @param email
   * @param name
   * @param lastName
   * @return new customer
   */
  @GraphQLMutation(name = "createCustomer", description = "Create or update a Customer based on data given")
  public Customer createCustomer(@GraphQLNonNull String email, @GraphQLNonNull String name, String lastName) {
    CustomerDto customerDto = CustomerDto.builder().uuid(EmailToUUID.generateUUID(email)).email(email).name(name).lastName(lastName).build();
    return customerInputPort.createCustomer(customerDto).getBody();
  }

  /**
   * Update a Customer based on data given.
   * @param uuid
   * @param email
   * @param name
   * @param lastName
   * @return updated customer
   */
  @GraphQLMutation(name = "updateCustomer", description = "Update a Customer based on data given")
  public Customer updateCustomer(@GraphQLNonNull UUID uuid, @GraphQLNonNull String email,
                                 @GraphQLNonNull String name, String lastName) {
    CustomerDto customerDto = CustomerDto.builder().uuid(uuid).email(email).name(name).lastName(lastName).build();
    return customerInputPort.createCustomer(customerDto).getBody();
  }

  /**
   * Get a Customer based on uuid given.
   * @param uuid
   * @return the customer data.
   */
  @GraphQLQuery(name = "customer", description = "Get a Customer based on uuid given")
  public Customer getCustomer(@GraphQLArgument(name = "uuid", description = "uuid of customer to find") final UUID uuid) {
    return customerInputPort.getCustomerById(uuid).getBody();
  }

  /**
   * Get a Customer based on email given.
   * @param email
   * @return customer data.
   */
  @GraphQLQuery(name = "customerByEmail", description = "Get a Customer based on email given")
  public Customer getCustomerByEmail(@GraphQLArgument(name = "email", description = "email of customer to search") String email) {
    return customerInputPort.getCustomerByEmail(email).getBody();
  }

  /**
   * Get all customers.
   *
   * @return list of customers.
   */
  @GraphQLQuery(name = "customers", description = "Get All Customers")
  public List<Customer> getAllCustomers() {
    log.info("getAllCustomers in process...");
    return customerInputPort.getAllCustomers().getBody();
  }

  /**
   * Deletes the customer given its uuid
   * @param uuid
   * @return String message of result
   */
  @GraphQLMutation(name = "delete", description = "Deletes the customer given its uuid")
  public String deleteCustomer(@GraphQLNonNull UUID uuid) {
    log.info("deleteById in process...");
    Boolean result = customerInputPort.deleteCustomerById(uuid);
    if (Boolean.TRUE.equals(result)) {
      return "Customer with uuid: " + uuid + " was successfully deleted!";
    } else {
      return "Error occurred when trying to deleted Customer with uuid:" + uuid + " or record don't exist.";
    }
  }

}
