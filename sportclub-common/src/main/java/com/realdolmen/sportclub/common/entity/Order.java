package com.realdolmen.sportclub.common.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ordr")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private boolean isPaid;

    @OneToMany
    private List<Orderable> orderables = new ArrayList<>();

    private BigDecimal price;

    @ManyToOne
    private User user;

    private UUID identifier;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PrePersist
    public void initializeUUID(){
        identifier = UUID.randomUUID();
    }

}
