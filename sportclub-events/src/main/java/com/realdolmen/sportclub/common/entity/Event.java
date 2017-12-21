package com.realdolmen.sportclub.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid = UUID.randomUUID().toString();

    @OneToMany
    @NotNull
    private List<RegisteredUser> responsibles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "event_enrollment", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "enrollment_id"))

    private List<Enrollment> enrollments = new ArrayList<>();

    @JsonIgnore
    @Lob
    private byte[] image;
    private String imageMimeType;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime startDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime endDate;

    @Embedded
    @NotNull
    private Address address;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
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

    @OneToMany(mappedBy = "event")
    @JsonIgnoreProperties("event")
    private List<Attendance> attendancies = new ArrayList<>();

    @JsonIgnore
    @Lob
    private byte[] attachement;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime reminderDate;

    // when reached this number of participants the moderators of this event want to get a mail
    private int numberParticipantsToSendMail;

    
    @Min(0)
    private int points;
    
    public int getPoints() {
        return points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }

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

    public LocalDateTime getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(LocalDateTime reminderDate) {
        this.reminderDate = reminderDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageMimeType() {
        return imageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberParticipantsToSendMail() { return numberParticipantsToSendMail; }

    public void setNumberParticipantsToSendMail(int numberParticipantsToSendMail) { this.numberParticipantsToSendMail = numberParticipantsToSendMail; }
}
