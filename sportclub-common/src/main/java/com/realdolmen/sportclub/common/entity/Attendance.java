package com.realdolmen.sportclub.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("attendance")
public class Attendance extends Orderable {
    @NotNull
    @Enumerated(EnumType.STRING)
    private AgeCategory ageCategory;

    @ManyToOne
    private Event event;

    private boolean cancelled;

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

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
