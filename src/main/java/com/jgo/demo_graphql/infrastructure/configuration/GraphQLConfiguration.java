package com.jgo.demo_graphql.infrastructure.configuration;

import graphql.GraphQL;
import io.leangen.graphql.GraphQLSchemaGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class GraphQLConfiguration {

  @Bean
  public GraphQL graphQLSchemaConfiguration (Object[] entitiesQuery) {
    return GraphQL.newGraphQL(new GraphQLSchemaGenerator()
        .withBasePackages("com.jgo.demo_graphql")
        .withOperationsFromSingletons(entitiesQuery)
        .generate())
        .build();
  }

}
