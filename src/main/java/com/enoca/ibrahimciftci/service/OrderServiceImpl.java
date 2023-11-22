package com.enoca.ibrahimciftci.service;

import com.enoca.ibrahimciftci.dto.OrderDto;
import com.enoca.ibrahimciftci.model.Order;
import com.enoca.ibrahimciftci.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDto> getOrders() {
        return orderRepository.findAll().stream().map(OrderDto::fromModel).collect(Collectors.toList());
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
    public OrderDto updateOrder(int id, OrderDto orderDto) {
        Order order = findById(id);
        order.setCreateDate(orderDto.getCreateDate());
        order.setTotalPrice(orderDto.getTotalPrice());

        order = orderRepository.save(order);
        return OrderDto.fromModel(order);
    }
}
