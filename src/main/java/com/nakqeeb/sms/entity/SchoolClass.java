package com.nakqeeb.sms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "classes")
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Course subject;

    private String classRoomNumber;

}