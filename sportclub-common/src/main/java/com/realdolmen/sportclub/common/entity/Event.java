package com.realdolmen.sportclub.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "event")
    @NotNull
    private List<RegisteredUser> responsibles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "event_enrollment", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "enrollment_id"))
    @NotNull
    private List<Enrollment> enrollments = new ArrayList<>();

    @Column
    private String imageUrl;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @Embedded
    @NotNull
    private Address address;

    @NotNull
    private LocalDateTime deadline;

    @NotNull
    private boolean isClosed;

    @NotNull
    private Map<String, BigDecimal> price = new HashMap<>();

    @NotNull
    private int minParticipants;

    @NotNull
    private int maxParticipants;

    private String description;

    @NotNull
    private String name;

    @Column
    private Long recurringEventId;

    public Long getId() {
        return id;
    }

    public List<RegisteredUser> getResponsibles() {
        return responsibles;
    }

    public void setResponsibles(List<RegisteredUser> responsibles) {
        this.responsibles = responsibles;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Map<String, BigDecimal> getPrice() {
        return price;
    }

    public void setPrice(Map<String, BigDecimal> price) {
        this.price = price;
    }

    public int getMinParticipants() {
        return minParticipants;
    }

    public void setMinParticipants(int minParticipants) {
        this.minParticipants = minParticipants;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRecurringEventId() {
        return recurringEventId;
    }

    public void setRecurringEventId(Long recurringEventId) {
        this.recurringEventId = recurringEventId;
    }
}
