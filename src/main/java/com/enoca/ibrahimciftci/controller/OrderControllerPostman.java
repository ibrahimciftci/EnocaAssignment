package com.enoca.ibrahimciftci.controller;

import com.enoca.ibrahimciftci.dto.OrderDto;
import com.enoca.ibrahimciftci.model.Customer;
import com.enoca.ibrahimciftci.model.Order;
import com.enoca.ibrahimciftci.service.CustomerService;
import com.enoca.ibrahimciftci.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderControllerPostman {
    private final OrderService orderService;
    private final CustomerService customerService;

    public OrderControllerPostman(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrder(){
        List<Order> orderList = orderService.getOrders();
        return ResponseEntity.ok(orderList.stream().map(OrderDto::fromModel).collect(Collectors.toList()));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId){
        Order order = orderService.findById(orderId);
        if (order == null){
            throw new RuntimeException("Order is not found!");
        }
        return ResponseEntity.ok(order);
    }

   @PostMapping("/{CustomerId}")
    public ResponseEntity<String> saveOrder(@PathVariable int CustomerId, @RequestBody OrderDto orderDto){

        try {
            Customer customer = customerService.findById(CustomerId);
            if (customer != null) {
                Order order = Order.fromDto(orderDto);
                order.setCustomer(customer);

                orderService.saveOrder(OrderDto.fromModel(order));
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity deleteById(@PathVariable int orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable int orderId, @RequestBody OrderDto orderDto) throws ParseException {
        return ResponseEntity.ok(orderService.updateOrder(orderId,orderDto));
    }
}
