package com.enoca.ibrahimciftci.dto;

import com.enoca.ibrahimciftci.model.Customer;
import com.enoca.ibrahimciftci.model.Order;

import java.util.Date;

public class OrderDto {
    private int id;
    private Date createDate;
    private double totalPrice;

    private Customer customer;

    public OrderDto(int id, Date createDate, double totalPrice, Customer customer) {
        this.id = id;
        this.createDate = createDate;
        this.totalPrice = totalPrice;
        this.customer = customer;
    }

    public OrderDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
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
        return new OrderDto(order.getId(),order.getCreateDate(),order.getTotalPrice(),order.getCustomer());
    }
}
