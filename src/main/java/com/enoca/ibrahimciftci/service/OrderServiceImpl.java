package com.enoca.ibrahimciftci.service;

import com.enoca.ibrahimciftci.dto.OrderDto;
import com.enoca.ibrahimciftci.model.Order;
import com.enoca.ibrahimciftci.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Order order1=Order.fromDto(orderDto);
        Order order = orderRepository.save(order1);
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

    @Override
    public OrderDto updateOrder(int id, OrderDto orderDto){
            Order order = findById(id);
            order.setCreateDate(parseStringToDate(orderDto.getCreateDate()));
            order.setTotalPrice(orderDto.getTotalPrice());
            order = orderRepository.save(order);
            return OrderDto.fromModel(order);
        }


        private Date parseStringToDate(String dateString) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return dateFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

