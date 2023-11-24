package com.enoca.ibrahimciftci.service;

import com.enoca.ibrahimciftci.dto.OrderDto;
import com.enoca.ibrahimciftci.model.Order;
import com.enoca.ibrahimciftci.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public OrderDto saveOrder(OrderDto orderDto) {
        Order order = orderRepository.save(Order.fromDto(orderDto));
        return OrderDto.fromModel(order);
    }


    @Override
    public boolean deleteOrder(int id) {
        Order tempOrder = findById(id);
        if (tempOrder == null){
            throw new RuntimeException("Order is not found!");
        }orderRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Order> afterOrders(Date date) {
        List<Order> orderList = orderRepository.findAll();
        List<Order> afterOrderList = new ArrayList<>();
        for (Order order : orderList){
            if(order.getCreateDate().after(date)){
                afterOrderList.add(order);
            }
        }
        return afterOrderList;
    }
/*
    @Override
    public OrderDto updateOrder(int id, OrderDto orderDto) {
        Order order = findById(id);
        order.setCreateDate(orderDto.getCreateDate());
        order.setTotalPrice(orderDto.getTotalPrice());

        order = orderRepository.save(order);
        return OrderDto.fromModel(order);
    }



     */
}
