package com.realdolmen.sportclub.common.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordr")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private boolean isPaid;

    @OneToMany
    private List<Orderable> orderables = new ArrayList<>();

    @Transient
    private BigDecimal price;

    @ManyToOne
    private RegisteredUser registeredUser;

    public Long getId() {
        return id;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public List<Orderable> getOrderables() {
        return orderables;
    }

    public void setOrderables(List<Orderable> orderables) {
        this.orderables = orderables;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }
}
