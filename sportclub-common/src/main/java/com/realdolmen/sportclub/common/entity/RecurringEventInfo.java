package com.realdolmen.sportclub.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class RecurringEventInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    @ElementCollection(targetClass=DayOfWeek.class)
    @Enumerated(EnumType.STRING)
       private Set<DayOfWeek> weekdays;
}
