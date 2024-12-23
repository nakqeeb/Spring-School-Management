package com.nakqeeb.sms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "timetable")
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    private String dayOfWeek;
    private String timeSlot;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Course subject;

}