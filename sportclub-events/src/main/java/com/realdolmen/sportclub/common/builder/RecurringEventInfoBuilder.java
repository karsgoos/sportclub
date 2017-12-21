package com.realdolmen.sportclub.common.builder;

import com.realdolmen.sportclub.common.entity.RecurringEventInfo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public final class RecurringEventInfoBuilder {
    private LocalDateTime startDate = LocalDateTime.now();
    private LocalDateTime endDate = startDate.plusMonths(5).plusHours(5);
    private Set<DayOfWeek> weekdays = new TreeSet<>();

    public RecurringEventInfoBuilder() {
    }

    public static RecurringEventInfoBuilder recurringEventInfo() {
        return new RecurringEventInfoBuilder();
    }

    public RecurringEventInfoBuilder startDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public RecurringEventInfoBuilder endDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public RecurringEventInfoBuilder weekdays(Set<DayOfWeek> weekdays) {
        this.weekdays = weekdays;
        return this;
    }

    public RecurringEventInfo build() {
        RecurringEventInfo recurringEventInfo = new RecurringEventInfo();
        recurringEventInfo.setStartDate(startDate);
        recurringEventInfo.setEndDate(endDate);
        recurringEventInfo.setWeekdays(weekdays);
        return recurringEventInfo;
    }
}
