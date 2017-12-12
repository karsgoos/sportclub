package com.realdolmen.sportclub.common.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isPaid;

    @OneToMany(mappedBy = "order")
    private List<Orderable> orderables;

    @Transient
    private BigDecimal price;

    @ManyToOne
    private RegisteredUser registeredUser;



}
