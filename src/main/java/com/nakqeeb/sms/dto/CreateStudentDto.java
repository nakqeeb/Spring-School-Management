package com.nakqeeb.sms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CreateStudentDto {
    @NotBlank(message = "Student name is required")
    private String name;

    @Email(message = "Valid email is required")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private Long parentId; // Optional, for parent association
    private Long classId;  // Optional, for class association

    @NotNull(message = "Enrollment date is required")
    private Date enrollmentDate;
}