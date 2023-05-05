package com.jgo.demo_graphql.infrastructure.configuration;

import com.jgo.demo_graphql.domain.model.Customer;
import com.mongodb.DuplicateKeyException;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class InsertDataMongo {

  private final MongoTemplate mongoTemplate;

  @Autowired
  public InsertDataMongo(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @PostConstruct
  public void insertCustomers() {
    log.info("Executing insertCustomers");
    Customer[] customers = {
        new Customer(UUID.fromString("729c5d13-261a-4b46-bb11-9af04cb2cd3e"), "bbunny@mail.com", "Bugs", "Bunny"),
        new Customer(UUID.fromString("729c5d13-261a-4b46-bb11-9af04cb2cd3f"), "lbunny@mail.com", "Lola", "Bunny"),
        new Customer(UUID.fromString("1adfa52b-a57c-3b40-8da4-388526f6595b"), "plucas@mail.com", "Pato", "Lucas"),
        new Customer(UUID.fromString("1adfa52b-a57c-3b40-8da4-388526f6595c"), "sgonzales@mail.com", "Speedy", "Gonzales"),
        new Customer(UUID.fromString("729c5d13-261a-4b46-bb11-9af04cb2cd3a"), "plepew@mail.com", "Pepe", "Le Pew"),
        new Customer(UUID.fromString("1adfa52b-a57c-3b40-8da4-388526f6595d"), "ddtasmania@mail.com", "Demonio", "De Tasmania"),
        new Customer(UUID.fromString("7b2e3405-5b5f-4d72-9a36-96da47e7b273"), "john.doe@example.com", "John", "Doe"),
        new Customer(UUID.fromString("14e46437-1bdd-4a8e-ac81-085d286e7aba"), "jgo@mail.com", "Julian", "Garcia O")
    };

    if (isCollectionEmpty("customer")) {
      // Insert the customers into the MongoDB database
      // insert with insertAll
      // mongoTemplate.insertAll(Arrays.asList(customers));

      // insert with foreach loop, allow to manage each record.
      for (Customer customer : customers) {
        try {
          mongoTemplate.insert(customer, "customer");
        } catch (DuplicateKeyException e) {
          log.error("Failed to insert customer {}: {}", customer.getUuid(), e.getMessage());
        }
      }
    }

  }

  public boolean isCollectionEmpty(String collectionName) {
    long count = mongoTemplate.count(new Query(), collectionName);
    log.info("Documents in {}: {}", collectionName, count);
    return count == 0;
  }

}
