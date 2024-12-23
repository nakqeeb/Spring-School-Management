package com.nakqeeb.sms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType type; // For distinguishing between teacher/student notifications

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    private Student student; // Nullable for notifications sent to teachers

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = true)
    private Teacher teacher; // Nullable for notifications sent to students
}

