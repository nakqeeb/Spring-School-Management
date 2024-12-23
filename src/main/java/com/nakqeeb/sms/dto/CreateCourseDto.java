package com.nakqeeb.sms.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateCourseDto {
    @NotBlank
    private String courseName;

    private String description;

//    @NotNull
//    private Long teacherId;

    @NotNull
    private Integer credits;

    private String syllabusUrl;
}