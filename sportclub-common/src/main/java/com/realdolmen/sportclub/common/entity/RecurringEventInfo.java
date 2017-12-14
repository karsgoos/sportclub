package com.realdolmen.sportclub.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class RecurringEventInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy/MM/dd HH:mm")
    private LocalDateTime startDate;

    @NotNull
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy/MM/dd HH:mm")
    private LocalDateTime endDate;

    @NotNull
    @ElementCollection(targetClass=DayOfWeek.class)
    @Enumerated(EnumType.STRING)
       private Set<DayOfWeek> weekdays;

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Set<DayOfWeek> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(Set<DayOfWeek> weekdays) {
        this.weekdays = weekdays;
    }
}
