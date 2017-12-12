package com.realdolmen.sportclub.common.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "event")
    private List<RegisteredUser> responsibles;

    @OneToMany(mappedBy = "enrollment")
    private List<Enrollment> enrollments;

    private String name;

    @Column(nullable = true)
    private String imageUrl;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Embedded
    private Address address;

}
