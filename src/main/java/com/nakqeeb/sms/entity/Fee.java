package com.nakqeeb.sms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "fees")
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Double amount;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private PaidStatusEnum paidStatus;

    @Temporal(TemporalType.DATE)
    private Date paymentDate;

}