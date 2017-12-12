package com.realdolmen.sportclub.common.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("attendance")
public class Attendance extends Orderable {
    @NotNull
    @Enumerated(EnumType.STRING)
    private AgeCategory ageCategory;

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }
}
