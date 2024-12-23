package com.nakqeeb.sms.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "teacher_id")
//    private Teacher teacher;

    private Integer credits;
    private String syllabusUrl;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TeacherSubject> teacherSubjects = new ArrayList<>();

}