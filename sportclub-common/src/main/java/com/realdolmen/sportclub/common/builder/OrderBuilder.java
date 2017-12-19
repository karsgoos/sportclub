package com.realdolmen.sportclub.common.builder;

import com.realdolmen.sportclub.common.entity.Order;
import com.realdolmen.sportclub.common.entity.Orderable;
import com.realdolmen.sportclub.common.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class OrderBuilder {
    private LocalDate orderDate = LocalDate.now();
    private List<Orderable> orderables = new ArrayList<>();
    private User user = new RegisteredUserBuilder().build();

    public OrderBuilder() {
    }

    public static OrderBuilder anOrder() {
        return new OrderBuilder();
    }

    public OrderBuilder orderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public OrderBuilder orderables(List<Orderable> orderables) {
        this.orderables = orderables;
        return this;
    }

    public OrderBuilder user(User user) {
        this.user = user;
        return this;
    }

    public Order build() {
        Order order = new Order();
        order.setOrderDate(orderDate);
        order.setOrderables(orderables);
        order.setUser(user);
        return order;
    }
}
