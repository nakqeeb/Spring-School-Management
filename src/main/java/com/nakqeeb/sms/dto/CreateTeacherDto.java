package com.nakqeeb.sms.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class CreateTeacherDto {

    @NotNull(message = "User ID is required.")
    private Long userId;

    @NotEmpty(message = "Qualification is required.")
    private String qualification;

//    @NotEmpty(message = "Subjects taught cannot be empty.")
//    private List<Long> subjectIds;
}