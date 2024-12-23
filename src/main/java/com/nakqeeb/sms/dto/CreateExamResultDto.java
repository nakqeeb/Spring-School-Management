package com.nakqeeb.sms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateExamResultDto {
    @NotNull(message = "Exam ID is required.")
    private Long examId;

    @NotNull(message = "Student ID is required.")
    private Long studentId;

    @NotNull(message = "Marks obtained are required.")
    private Integer marksObtained;

    private String grade;
}