package com.realdolmen.sportclub.common.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("enrollment")
public class Enrollment extends Orderable {
    @NotNull
    @Column
    private LocalDate startDate;
    @NotNull
    @Column
    private LocalDate endDate;

    @NotNull
    @ManyToOne
    private MembershipType membershipType;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
