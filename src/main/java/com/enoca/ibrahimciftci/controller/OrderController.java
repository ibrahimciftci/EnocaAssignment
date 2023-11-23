package com.enoca.ibrahimciftci.controller;


import com.enoca.ibrahimciftci.dto.OrderDto;
import com.enoca.ibrahimciftci.model.Order;
import com.enoca.ibrahimciftci.service.DateService;
import com.enoca.ibrahimciftci.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/'orders'")
public class OrderController {
    private final OrderService orderService;
    private final DateService dateService;

    public OrderController(OrderService orderService, DateService dateService) {
        this.orderService = orderService;
        this.dateService = dateService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrder(){
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id){
        Order order = orderService.findById(id);
        if (order == null){
            throw new RuntimeException("Order is not found!");
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.saveOrder(orderDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable int id, @RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.updateOrder(id,orderDto));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Order>> getAfterDateOrder(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return ResponseEntity.ok(dateService.afterOrders(date));
    }
}
