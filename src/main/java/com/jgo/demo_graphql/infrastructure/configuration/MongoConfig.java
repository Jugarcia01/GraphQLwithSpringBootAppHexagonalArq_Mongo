package com.jgo.demo_graphql.infrastructure.configuration;

import com.jgo.demo_graphql.domain.model.Customer;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

  @Value("${spring.data.mongodb.host}")
  private String host;

  @Value("${spring.data.mongodb.port}")
  private String port;

  @Value("${spring.data.mongodb.database}")
  private String db;

  // @Override
  @Bean
  public MongoClient mongoClient() {
    log.info("Executing mongoClient");
    String mongoUri = "mongodb://"+host+":"+port+"/"+db;

    // Specifying the UUID representation as standard or JAVA_LEGACY
    UuidRepresentation uuidRepresentation = UuidRepresentation.STANDARD;

    ConnectionString connectionString = new ConnectionString(mongoUri);
    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .uuidRepresentation(uuidRepresentation)
        .build();

    return MongoClients.create(mongoClientSettings);
  }

  @Override
  protected String getDatabaseName() {
    log.info("Executing getDatabaseName");
    return db;
  }

  @Bean
  public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) {
    log.info("Executing mongoTemplate");
      return mongoTemplate(mongoDatabaseFactory);
  }

}
