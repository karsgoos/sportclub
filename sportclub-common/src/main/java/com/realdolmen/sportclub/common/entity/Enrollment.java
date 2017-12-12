package com.realdolmen.sportclub.common.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("enrollment")
public class Enrollment extends Orderable {
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;

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
