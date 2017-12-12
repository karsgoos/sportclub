package com.realdolmen.sportclub.common.entity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("attendance")
public class Attendance extends Orderable {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgeCategory ageCategory;

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }
}
