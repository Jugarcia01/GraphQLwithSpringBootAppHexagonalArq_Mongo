package com.jgo.demo_graphql.domain.model;

//import com.jgo.demoGraphQL.util.SelfValidating;
// import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
//public class Order extends SelfValidating<Order> implements Serializable {

  private Long id;
  @JsonProperty("nombre")
  private String name;
  @JsonProperty("descripcion")
  private String description;
  @JsonProperty("cliente")
  private Customer customer;
  @JsonProperty("saleDetails")
  private List<SaleDetails> saleDetails;
  @JsonProperty("totalOrden")
  private BigDecimal totalOrder;

  public Order addCustomer(Customer customer) {
    this.customer = customer;
    return this;
  }

  public Order addSaleDetails(SaleDetails saleDetails) {
    this.saleDetails.add(saleDetails);
    return this;
  }

}
