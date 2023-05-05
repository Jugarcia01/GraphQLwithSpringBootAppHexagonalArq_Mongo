package com.jgo.demo_graphql.infrastructure.inputport;

import com.jgo.demo_graphql.domain.model.Order;
import java.util.List;

public interface OrderDaoInputPort {

  public Order createOrder(Order order, Boolean isUpdate);

  public Order getOrderById(Long id);

  public Order deleteOrderById(Long id);

  public List<Order> getByCustomerId(Long customerId);

  public List<Order> getAllOrders();

}
