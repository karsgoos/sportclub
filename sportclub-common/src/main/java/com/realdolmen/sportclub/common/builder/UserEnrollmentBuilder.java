package com.realdolmen.sportclub.common.builder;

import com.realdolmen.sportclub.common.entity.Enrollment;
import com.realdolmen.sportclub.common.entity.Order;
import com.realdolmen.sportclub.common.entity.UserEnrollment;

import java.math.BigDecimal;

public final class UserEnrollmentBuilder {
    private Enrollment enrollment = new EnrollmentBuilder().build();
    private BigDecimal price = new BigDecimal(100);
    private String description = "A user enrollment example";
    private Order ordr = new OrderBuilder().build();

    public UserEnrollmentBuilder() {
    }

    public static UserEnrollmentBuilder userEnrollment() {
        return new UserEnrollmentBuilder();
    }

    public UserEnrollmentBuilder enrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
        return this;
    }


    public UserEnrollmentBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public UserEnrollmentBuilder description(String description) {
        this.description = description;
        return this;
    }

    public UserEnrollmentBuilder ordr(Order ordr) {
        this.ordr = ordr;
        return this;
    }

    public UserEnrollment build() {
        UserEnrollment userEnrollment = new UserEnrollment();
        userEnrollment.setEnrollment(enrollment);
        userEnrollment.setPrice(price);
        userEnrollment.setDescription(description);
        userEnrollment.setOrdr(ordr);
        return userEnrollment;
    }
}
