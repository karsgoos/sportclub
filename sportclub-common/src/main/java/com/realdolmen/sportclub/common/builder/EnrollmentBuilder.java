package com.realdolmen.sportclub.common.builder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.realdolmen.sportclub.common.entity.Enrollment;
import com.realdolmen.sportclub.common.entity.MembershipType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EnrollmentBuilder {

    private Long id;

    private String name;

    private LocalDate startDate = LocalDate.now().plusWeeks(1);

    private LocalDate endDate = startDate.plusMonths(6);

    private BigDecimal price = new BigDecimal(100);

    private MembershipType membershipType = new MembershipTypeBuilder().build();

    public EnrollmentBuilder name(String name){
        this.name = name;
        return this;
    }

    public EnrollmentBuilder startDate(LocalDate startDate){
        this.startDate = startDate;
        return this;
    }

    public EnrollmentBuilder endDate(LocalDate endDate){
        this.endDate = endDate;
        return this;
    }

    public EnrollmentBuilder membershipType(MembershipType membershipType){
        this.membershipType = membershipType;
        return this;
    }

    public EnrollmentBuilder price(BigDecimal price){
        this.price = price;
        return this;
    }


    public Enrollment build(){
        Enrollment enrollment = new Enrollment();
        enrollment.setName(this.name);
        enrollment.setStartDate(this.startDate);
        enrollment.setEndDate(this.endDate);
        enrollment.setMembershipType(this.membershipType);
        enrollment.setPrice(this.price);
        return enrollment;
    }

}
