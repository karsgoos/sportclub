package com.realdolmen.sportclub.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ordr")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern="yyyy/MM/dd")
    private LocalDate orderDate;


    private boolean isPaid;

    @OneToMany(mappedBy = "ordr")
    @JsonIgnoreProperties("ordr")
    private List<Orderable> orderables = new ArrayList<>();

    @Transient
    private BigDecimal price = BigDecimal.ZERO;

    @ManyToOne
    private User user;
    
    public UUID getIdentifier() {
        return identifier;
    }
    
    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }
    
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
    
    public void addOrderable(Orderable orderable){
           this.orderables.add(orderable);
       }

    public BigDecimal getPrice() {
        return this.calculateTotalPrice();
    }

    private void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @PostConstruct
    public BigDecimal calculateTotalPrice(){
           this.price = this.orderables.stream().map(Orderable::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
           return this.price;
    }

    @PrePersist
    public void initializeUUID(){
        identifier = UUID.randomUUID();
    }

}
