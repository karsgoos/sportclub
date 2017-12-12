package com.realdolmen.sportclub.common.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(columnDefinition = "order_type")
public abstract class Orderable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal price;
    @NotNull
    private String description;
}
