package com.enoca.ibrahimciftci.controller;

import com.enoca.ibrahimciftci.dto.OrderDto;
import com.enoca.ibrahimciftci.model.Customer;
import com.enoca.ibrahimciftci.model.Order;
import com.enoca.ibrahimciftci.repository.OrderRepository;
import com.enoca.ibrahimciftci.service.CustomerService;
import com.enoca.ibrahimciftci.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/'orders'")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final OrderRepository orderRepository;

    public OrderController(OrderService orderService, CustomerService customerService,
                           OrderRepository orderRepository) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/list")
    public String getAllOrder(@RequestParam("customerId") int customerId,Model model){
        Customer customer = customerService.findById(customerId);
        List<Order> orderList = customer.getOrders().stream().collect(Collectors.toList());
        model.addAttribute("orders", orderList);
        model.addAttribute("customer", customer);
        return "list-orders";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model, @RequestParam("customerId") int customerId){
        OrderDto orderDto = new OrderDto();
        Customer customer = customerService.findById(customerId);
        model.addAttribute("order", orderDto);
        model.addAttribute("customer", customer);
        return  "order-form";
    }


    @PostMapping("/save")
    public String saveOrder(@RequestParam("id") int id,@ModelAttribute("order") Order order){

            Customer customer = customerService.findById(id);
            System.err.println(customer);
            customer.addOrder(order);
            orderService.saveOrder(OrderDto.fromModel(order));

        return "redirect:/'orders'/list";
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id){
        Order order = orderService.findById(id);
        if (order == null){
            throw new RuntimeException("Order is not found!");
        }
        return ResponseEntity.ok(order);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable int id, @RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.updateOrder(id,orderDto));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Order>> getAfterDateOrder(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return ResponseEntity.ok(orderService.afterOrders(date));
    }
}
