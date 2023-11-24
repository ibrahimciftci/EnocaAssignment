package com.enoca.ibrahimciftci.controller;

import com.enoca.ibrahimciftci.dto.CustomerDto;
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
@RequestMapping("/orders")
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
    public String showFormForAdd(Model model, @RequestParam("id") int id){
        OrderDto orderDto = new OrderDto();
        Customer customer = customerService.findById(id);
        model.addAttribute("order", orderDto);
        model.addAttribute("customer", customer);
        return  "order-form";
    }


    @PostMapping("/save")
    public String saveOrder(@RequestParam("id") int id,@ModelAttribute("order") OrderDto orderDto){

            Customer customer = customerService.findById(id);
            System.err.println(customer);

            /*Order order = Order.fromDto(orderDto);
            customer.addOrder(order);
            orderService.saveOrder(OrderDto.fromModel(order));
            */

            return "redirect:/orders/list?customerId=" +id;
    }

    @GetMapping("/deleteOrder")
    public String deleteById(@RequestParam("orderId") int orderId){
        orderService.deleteOrder(orderId);
        return "redirect:/orders/list?orderId=" +orderId;
    }

    @GetMapping("/showFormForUpdate")
    public String updateOrder(@RequestParam("orderId") int orderId, Model model){
        Order order = orderService.findById(orderId);
        OrderDto orderDto = OrderDto.fromModel(order);
        model.addAttribute("order", orderDto);
        return "order-form";
    }


    //http://localhost:8080/orders/date/2020-11-23
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Order>> getAfterDateOrder(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return ResponseEntity.ok(orderService.afterOrders(date));
    }








    /*
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id){
        Order order = orderService.findById(id);
        if (order == null){
            throw new RuntimeException("Order is not found!");
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable int id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable int id, @RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.updateOrder(id,orderDto));
    }


    */

}
