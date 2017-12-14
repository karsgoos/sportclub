package com.realdolmen.sportclub.common.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "order_type")
public abstract class Orderable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal price;
    @NotNull
    private String description;

    @ManyToOne
    private Order ordr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Order getOrdr() {
        return ordr;
    }

    public void setOrdr(Order ordr) {
        this.ordr = ordr;
    }
}
