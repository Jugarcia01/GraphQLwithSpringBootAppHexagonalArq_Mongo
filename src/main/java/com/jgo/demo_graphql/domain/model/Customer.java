package com.jgo.demo_graphql.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// //-- Enable when use Postgres DB
// @Entity
// @Table(name = "CUSTOMER")
//-- Enable when use MongoDB
@Document(collection = "customer")
public class Customer {

    //-- Enable when use Postgres DB
    // @javax.persistence.Id
    // @JsonProperty("uuid")
    // @GraphQLQuery(name = "uuid")
    // @GeneratedValue(strategy = GenerationType.AUTO)
    //--  Enable when use H2
    // @Column(name = "uuid", nullable = false, updatable = false, unique = true)
    // @Type(type="org.hibernate.type.UUIDCharType")
    // private UUID uuid;

    //-- Enable when use MongoDB
    @Id
    @JsonProperty("uuid")
    @GraphQLQuery(name = "uuid")
    private UUID uuid;
    //--

    @JsonProperty("email")
    @GraphQLQuery(name = "email")
    @Column(name = "email")
    private String email;

    @JsonProperty("name")
    @GraphQLQuery(name = "name")
    @Column(name = "name")
    private String name;

    @JsonProperty("lastName")
    @GraphQLQuery(name = "lastName")
    @Column(name = "lastname") // WARNING: Note the column name is in lower case.
    private String lastName;

    // Maybe we need to include this methods: equals, hashCode, toString

}
