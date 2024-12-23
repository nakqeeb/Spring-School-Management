package com.nakqeeb.sms.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class CreateExamDto {
    @NotBlank(message = "Exam name is required.")
    private String examName;

    @NotNull(message = "Class ID is required.")
    private Long classId;

    @NotNull(message = "Subject ID is required.")
    private Long subjectId;

    @NotNull(message = "Exam date is required.")
    private Date date;

    @NotNull(message = "Total marks are required.")
    private Integer totalMarks;
}