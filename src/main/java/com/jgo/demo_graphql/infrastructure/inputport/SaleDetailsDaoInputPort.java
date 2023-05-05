package com.jgo.demo_graphql.infrastructure.inputport;

import com.jgo.demo_graphql.domain.model.SaleDetails;
import java.util.List;

public interface SaleDetailsDaoInputPort {

  public SaleDetails createSaleDetails(SaleDetails saleDetails, Boolean isUpdate);

  public SaleDetails getSaleDetailsById(Long id);

  public SaleDetails deleteSaleDetailsById(Long id);

  public List<SaleDetails> getAllSaleDetails();

}
