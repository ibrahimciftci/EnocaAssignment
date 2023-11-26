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
import org.springframework.validation.BindingResult;
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
        orderDto.setCustomer(customer);
        model.addAttribute("order", orderDto);
        model.addAttribute("customerId", id);
        return  "order-form";
    }


    @PostMapping("/save")
    public String saveOrder( @ModelAttribute("order") OrderDto orderDto, BindingResult bindingResult)
    {
            Order order = Order.fromDto(orderDto);
            orderService.saveOrder(OrderDto.fromModel(order));


            return "redirect:/orders/list?customerId=" +order.getCustomer().getId();
    }

    @GetMapping("/deleteOrder")
    public String deleteById(@RequestParam("orderId") int orderId, @RequestParam("customerId") int customerId){
        orderService.deleteOrder(orderId);
        return "redirect:/orders/list?customerId=" +customerId;
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
}
