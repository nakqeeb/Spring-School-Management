package com.nakqeeb.sms.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String qualification;

//    @ElementCollection
//    @CollectionTable(name = "teacher_subjects", joinColumns = @JoinColumn(name = "teacher_id"))
//    @Column(name = "subject")
//    private List<String> subjectsTaught;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TeacherSubject> teacherSubjects = new ArrayList<>();

    @Transient
    private List<String> subjectNames;

    public List<String> getSubjectNames() {
        return teacherSubjects.stream()
                .map(teacherSubject -> teacherSubject.getSubject().getCourseName())
                .collect(Collectors.toList());
    }
}
