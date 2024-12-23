package com.nakqeeb.sms.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateClassDto {
    @NotBlank(message = "Class name is required.")
    private String className;

    @NotNull(message = "Teacher ID is required.")
    private Long teacherId;

    @NotNull(message = "Subject ID is required.")
    private Long subjectId;

    @NotBlank(message = "Classroom number is required.")
    private String classRoomNumber;
}