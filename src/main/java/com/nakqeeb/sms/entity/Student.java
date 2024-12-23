package com.nakqeeb.sms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Entity
//@Data
//@Table(name = "students")
//public class Student {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "class_id")
//    private Class aClass;
//
//    @Temporal(TemporalType.DATE)
//    private Date enrollmentDate;
//
//    @ManyToOne
//    @JoinColumn(name = "parent_id")
//    private User parent;
//
//}

@Entity
@Data
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @Temporal(TemporalType.DATE)
    private Date enrollmentDate;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private User parent;

//    @ElementCollection
//    @CollectionTable(name = "student_grades", joinColumns = @JoinColumn(name = "student_id"))
//    private List<Grade> grades = new ArrayList<>();

}

