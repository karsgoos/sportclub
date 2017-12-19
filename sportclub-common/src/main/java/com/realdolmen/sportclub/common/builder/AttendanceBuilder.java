package com.realdolmen.sportclub.common.builder;

import com.realdolmen.sportclub.common.entity.AgeCategory;
import com.realdolmen.sportclub.common.entity.Attendance;
import com.realdolmen.sportclub.common.entity.Event;
import com.realdolmen.sportclub.common.entity.Order;

import java.math.BigDecimal;

public final class AttendanceBuilder {
    private AgeCategory ageCategory;
    private Event event;
    private BigDecimal price;
    private String description;
    private Order ordr;

    public AttendanceBuilder() {
    }

    public static AttendanceBuilder anAttendance() {
        return new AttendanceBuilder();
    }

    public AttendanceBuilder ageCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
        return this;
    }

    public AttendanceBuilder id(Long id) {
        return this;
    }

    public AttendanceBuilder event(Event event) {
        this.event = event;
        return this;
    }

    public AttendanceBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public AttendanceBuilder description(String description) {
        this.description = description;
        return this;
    }

    public AttendanceBuilder ordr(Order ordr) {
        this.ordr = ordr;
        return this;
    }

    public Attendance build() {
        Attendance attendance = new Attendance();
        attendance.setAgeCategory(ageCategory);
        attendance.setEvent(event);
        attendance.setPrice(price);
        attendance.setDescription(description);
        attendance.setOrdr(ordr);
        return attendance;
    }
}
