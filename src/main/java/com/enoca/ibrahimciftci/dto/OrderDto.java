package com.enoca.ibrahimciftci.dto;

import com.enoca.ibrahimciftci.model.Customer;
import com.enoca.ibrahimciftci.model.Order;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;

public class OrderDto {
    private int id;

    @NotBlank(message = "createDate is required")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String createDate;
    private double totalPrice;

    private Customer customer;


    public OrderDto(int id, String createDate, double totalPrice,Customer customer) {
        this.id = id;
        this.createDate = createDate;
        this.totalPrice = totalPrice;
        this.customer=customer;
    }

    public OrderDto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", totalPrice=" + totalPrice +
                ", customer=" + customer +
                '}';
    }

    public static OrderDto fromModel(Order order){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String createDate=dateFormat.format(order.getCreateDate());
        return new OrderDto(order.getId(),createDate,order.getTotalPrice(),order.getCustomer());
    }
}
