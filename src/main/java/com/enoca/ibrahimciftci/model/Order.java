package com.enoca.ibrahimciftci.model;

import com.enoca.ibrahimciftci.dto.CustomerDto;
import com.enoca.ibrahimciftci.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;

import java.util.Date;

@Entity
@Table(name = "'order'")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id",insertable=false, updatable=false)
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order(Date createDate, double totalPrice, Customer customer) {
        this.createDate = createDate;
        this.totalPrice = totalPrice;
        this.customer = customer;
    }

    public Order() {
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
        return "Order{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", totalPrice=" + totalPrice +
                ", customer=" + customer +
                '}';
    }



    public static Order fromDto(OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreateDate(orderDto.getCreateDate());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setCustomer(orderDto.getCustomer());
        return order;
    }
}
