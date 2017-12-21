package com.realdolmen.sportclub.common.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    private String subject;

    @NotNull
    @Lob
    private String content;

    @NotNull
    private LocalDateTime dueDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SendStatus sendStatus;

    public Email() {
    }



    public Long getId() {
        return id;
    }


    public Email(@NotNull User user, @NotNull String subject, @NotNull String content, @NotNull LocalDateTime dueDate, @NotNull SendStatus sendStatus) {
        this.user = user;
        this.subject = subject;
        this.content = content;
        this.dueDate = dueDate;
        this.sendStatus = sendStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public SendStatus getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(SendStatus sendStatus) {
        this.sendStatus = sendStatus;
    }
}
