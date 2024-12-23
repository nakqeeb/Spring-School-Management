package com.nakqeeb.sms.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Date;

@Embeddable
@Data
public class Grade {

    private String subjectName;
    private String grade; // Example: A, B+, C, etc.
    private Date gradingDate;
}