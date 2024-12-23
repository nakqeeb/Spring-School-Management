package com.nakqeeb.sms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "exam_results")
public class ExamResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    private Integer marksObtained;
    private String grade;

}