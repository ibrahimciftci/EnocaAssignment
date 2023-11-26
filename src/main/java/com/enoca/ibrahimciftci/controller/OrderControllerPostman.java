package com.enoca.ibrahimciftci.controller;

import com.enoca.ibrahimciftci.dto.CustomerDto;
import com.enoca.ibrahimciftci.dto.OrderDto;
import com.enoca.ibrahimciftci.model.Customer;
import com.enoca.ibrahimciftci.model.Order;
import com.enoca.ibrahimciftci.service.CustomerService;
import com.enoca.ibrahimciftci.service.OrderService;
import jakarta.persistence.EntityManager;
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

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id){
        Order order = orderService.findById(id);
        if (order == null){
            throw new RuntimeException("Order is not found!");
        }
        return ResponseEntity.ok(order);
    }

 /*   @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {

        CustomerDto customerDto = CustomerDto.fromModel(customerService.findById(id));
        Customer customer = Customer.fromDto(customerDto);
        orderDto.setCustomer(customer);
        orderService.saveOrder(orderDto);

        return ResponseEntity.ok(orderDto);

    }
*/


   /* @PostMapping
    public ResponseEntity<String> saveOrder(@PathVariable int id, @RequestBody OrderDto orderDto){

        try {
            Customer customer = customerService.findById(id);
            if (customer != null) {
                Order order = Order.fromDto(orderDto);
                customer.addOrder(order);
                order.setCustomer(customer);
                customerService.saveCustomer(CustomerDto.fromModel(customer));
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable int id, @RequestBody OrderDto orderDto) throws ParseException {
        return ResponseEntity.ok(orderService.updateOrder(id,orderDto));
    }
}
