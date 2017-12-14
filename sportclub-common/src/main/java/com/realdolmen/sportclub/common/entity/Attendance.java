package com.realdolmen.sportclub.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("attendance")
public class Attendance extends Orderable {
    @NotNull
    @Enumerated(EnumType.STRING)
    private AgeCategory ageCategory;

    @ManyToOne
    @JsonIgnoreProperties("attendancies")
    private Event event;

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
