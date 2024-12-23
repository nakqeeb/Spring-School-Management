package com.nakqeeb.sms.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class UpdateTeacherDto {

    private String qualification;

//    @NotEmpty(message = "Subjects taught cannot be empty.")
//    private List<Long> subjectIds;
}