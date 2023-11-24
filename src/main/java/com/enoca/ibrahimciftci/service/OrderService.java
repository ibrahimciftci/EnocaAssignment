package com.enoca.ibrahimciftci.service;

import com.enoca.ibrahimciftci.dto.OrderDto;
import com.enoca.ibrahimciftci.model.Order;


import java.util.Date;
import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    Order findById(int id);

    OrderDto saveOrder(OrderDto orderDto);

    boolean deleteOrder(int id);

    List<Order> afterOrders(Date date);
/*
    OrderDto updateOrder(int id, OrderDto orderDto);



 */
}
