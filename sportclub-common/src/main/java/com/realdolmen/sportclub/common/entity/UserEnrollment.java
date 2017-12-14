package com.realdolmen.sportclub.common.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("user_enrollment")
public class UserEnrollment extends Orderable {
    @NotNull
    @ManyToOne
    private Enrollment enrollment;

    @NotNull
    @ManyToOne
    private MembershipType membershipType;

}
