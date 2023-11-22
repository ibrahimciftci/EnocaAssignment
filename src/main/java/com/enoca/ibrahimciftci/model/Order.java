package com.enoca.ibrahimciftci.model;

import com.enoca.ibrahimciftci.dto.OrderDto;
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

    public Order(Date createDate, double totalPrice) {
        this.createDate = createDate;
        this.totalPrice = totalPrice;
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
                '}';
    }

    public static Order fromDto(OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreateDate(orderDto.getCreateDate());
        order.setTotalPrice(orderDto.getTotalPrice());
        return order;
    }
}
