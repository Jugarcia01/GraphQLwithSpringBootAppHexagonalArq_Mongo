package com.jgo.demo_graphql.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
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
public class SaleDetails {

    private Long id;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("cantidad")
    private Long quantity;
    @JsonProperty("precioUnitario")
    private BigDecimal unitPrice;
    @JsonProperty("precioTotal")
    private BigDecimal totalPrice;
    @JsonProperty("discountAmount")
    private BigDecimal discountAmount;

}
