package com.realdolmen.sportclub.common.builder;

import com.realdolmen.sportclub.common.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class EventBuilder {
    private List<RegisteredUser> responsibles = new ArrayList<>();

    private List<Enrollment> enrollments = new ArrayList<>();
    private String imageUrl;
    private LocalDateTime startDate = LocalDateTime.now().plusWeeks(5);
    private LocalDateTime endDate = startDate.plusHours(2);
    private Address address = new AddressBuilder().build();
    private LocalDateTime deadline = startDate.minusDays(5);
    private BigDecimal priceAdult = new BigDecimal(10);
    private BigDecimal priceChild = new BigDecimal(5);
    private int minParticipants = 1;
    private int maxParticipants = 15;
    private String description = "Non filled in description";
    private String name = "Training event";
    private RecurringEventInfo recurringEventInfo;
    private byte[] attachement;
    private LocalDateTime reminderDate = startDate.minusDays(3);

    public EventBuilder() {
    }

    public static EventBuilder anEvent() {
        return new EventBuilder();
    }

    public EventBuilder withResponsibles(List<RegisteredUser> responsibles) {
        this.responsibles = responsibles;
        return this;
    }

    public EventBuilder withEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
        return this;
    }

    public EventBuilder withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public EventBuilder withStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public EventBuilder withEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public EventBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public EventBuilder withDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        return this;
    }

    public EventBuilder withPriceAdult(BigDecimal priceAdult) {
        this.priceAdult = priceAdult;
        return this;
    }

    public EventBuilder withPriceChild(BigDecimal priceChild) {
        this.priceChild = priceChild;
        return this;
    }

    public EventBuilder withMinParticipants(int minParticipants) {
        this.minParticipants = minParticipants;
        return this;
    }

    public EventBuilder withMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
        return this;
    }

    public EventBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public EventBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EventBuilder withRecurringEventInfo(RecurringEventInfo recurringEventInfo) {
        this.recurringEventInfo = recurringEventInfo;
        return this;
    }

    public EventBuilder withAttachement(byte[] attachement) {
        this.attachement = attachement;
        return this;
    }

    public EventBuilder withReminderDate(LocalDateTime reminderDate) {
        this.reminderDate = reminderDate;
        return this;
    }

    public Event build() {
        Event event = new Event();
        event.setResponsibles(responsibles);
        event.setEnrollments(enrollments);
        event.setImageUrl(imageUrl);
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setAddress(address);
        event.setDeadline(deadline);
        event.setPriceAdult(priceAdult);
        event.setPriceChild(priceChild);
        event.setMinParticipants(minParticipants);
        event.setMaxParticipants(maxParticipants);
        event.setDescription(description);
        event.setName(name);
        event.setRecurringEventInfo(recurringEventInfo);
        event.setAttachement(attachement);
        event.setReminderDate(reminderDate);
        return event;
    }
}
