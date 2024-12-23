package com.nakqeeb.sms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class EnrollDto {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Class ID is required")
    private Long classId;

    @NotNull(message = "Enrollment date is required")
    private Date enrollmentDate;

}