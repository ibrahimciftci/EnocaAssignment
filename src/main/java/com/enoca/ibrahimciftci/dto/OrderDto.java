package com.enoca.ibrahimciftci.dto;

import com.enoca.ibrahimciftci.model.Order;

import java.util.Date;

public class OrderDto {
    private int id;
    private Date createDate;
    private double totalPrice;

    public OrderDto(int id, Date createDate, double totalPrice) {
        this.id = id;
        this.createDate = createDate;
        this.totalPrice = totalPrice;
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

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public static OrderDto fromModel(Order order){
        return new OrderDto(order.getId(),order.getCreateDate(),order.getTotalPrice());
    }
}
