package com.enoca.ibrahimciftci.service;

import com.enoca.ibrahimciftci.dto.OrderDto;
import com.enoca.ibrahimciftci.model.Order;


import java.util.List;

public interface OrderService {
    List<OrderDto> getOrders();

    Order findById(int id);

    OrderDto saveOrder(OrderDto orderDto);

    boolean deleteOrder(int id);

    OrderDto updateOrder(int id, OrderDto orderDto);
}
