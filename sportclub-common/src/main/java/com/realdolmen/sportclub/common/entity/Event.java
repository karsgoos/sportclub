package com.realdolmen.sportclub.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @NotNull
    private List<RegisteredUser> responsibles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "event_enrollment", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "enrollment_id"))

    private List<Enrollment> enrollments = new ArrayList<>();

    @Column
    private String imageUrl;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy/MM/dd HH:mm")
    private LocalDateTime startDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy/MM/dd HH:mm")
    private LocalDateTime endDate;

    @Embedded
    @NotNull
    private Address address;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy/MM/dd HH:mm")
    private LocalDateTime deadline;

    @NotNull
    private boolean isClosed;

    /*@ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    @NotNull
    private Map<AgeCategory, BigDecimal> price = new HashMap<>();*/

    public BigDecimal getPriceAdult() {
        return priceAdult;
    }

    public void setPriceAdult(BigDecimal priceAdult) {
        this.priceAdult = priceAdult;
    }

    public BigDecimal getPriceChild() {
        return priceChild;
    }

    public void setPriceChild(BigDecimal priceChild) {
        this.priceChild = priceChild;
    }

    @NotNull

    private BigDecimal priceAdult;

    private BigDecimal priceChild;

    @NotNull
    private int minParticipants;

    @NotNull
    private int maxParticipants;

    private String description;

    @NotNull
    private String name;

    @ManyToOne
    private RecurringEventInfo recurringEventInfo;

    @OneToMany
    private List<Attendance> attendancies = new ArrayList<>();

    @JsonIgnore @Lob
    private byte[] attachement;

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

   /* public Map<AgeCategory, BigDecimal> getPrice() {
        return price;
    }

    public void setPrice(Map<AgeCategory, BigDecimal> price) {
        this.price = price;
    }*/

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

    public RecurringEventInfo getRecurringEventInfo() {
        return recurringEventInfo;
    }

    public void setRecurringEventInfo(RecurringEventInfo recurringEventInfo) {
        this.recurringEventInfo = recurringEventInfo;
    }

    public List<Attendance> getAttendancies() {
        return attendancies;
    }

    public void addAttendance(Attendance attendance) {
        attendancies.add(attendance);
        attendance.setEvent(this);
    }

    public void remAttendance(Attendance attendance) {
        attendancies.remove(attendance);
    }

    public byte[] getAttachement() {
        return attachement;
    }

    public void setAttachement(byte[] attachement) {
        this.attachement = attachement;
    }
}
