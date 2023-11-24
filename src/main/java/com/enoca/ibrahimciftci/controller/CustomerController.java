package com.enoca.ibrahimciftci.controller;

import com.enoca.ibrahimciftci.dto.CustomerDto;
import com.enoca.ibrahimciftci.model.Customer;
import com.enoca.ibrahimciftci.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String getAllCustomer(Model model){
        List<Customer> customerList = customerService.getCustomers();
        model.addAttribute("customers", customerList);
        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        CustomerDto customerDto = new CustomerDto();
        model.addAttribute("customer", customerDto);
        return  "customer-form";
    }


    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") CustomerDto customerDto){
        customerService.saveCustomer(customerDto);
        return "redirect:/customers/list";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id){
        Customer customer = customerService.findById(id);
        if (customer == null){
            throw new RuntimeException("Customer is not found!");
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/deleteCustomer")
    public String deleteById(@RequestParam("customerId") int customerId){
        customerService.deleteCustomer(customerId);
        return "redirect:/customers/list";
    }

    @GetMapping("/showFormForUpdate")
    public String updateCustomer(@RequestParam("customerId") int customerId, Model model){
        Customer customer = customerService.findById(customerId);
        CustomerDto customerDto = CustomerDto.fromModel(customer);
        model.addAttribute("customer", customerDto);
        return "customer-form";
    }

    @GetMapping("/search/{text}")
    public ResponseEntity<List<Customer>> findCustomerWithSearch(@PathVariable String text){
        return ResponseEntity.ok(customerService.searchCustomer(text));
    }


    @GetMapping("/getCustomerWithoutOrder")
    public List<Customer> getCustomerWithoutOrder(){
        return customerService.getCustomerWithoutOrder();
    }
}
