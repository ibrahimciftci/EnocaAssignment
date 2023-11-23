package com.enoca.ibrahimciftci.service;

import com.enoca.ibrahimciftci.model.Order;
import com.enoca.ibrahimciftci.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DateServiceImpl implements DateService{

    private final OrderRepository orderRepository;

    public DateServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
}
