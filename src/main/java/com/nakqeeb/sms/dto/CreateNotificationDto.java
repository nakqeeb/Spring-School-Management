package com.nakqeeb.sms.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CreateNotificationDto {

    @NotEmpty(message = "Message cannot be empty.")
    private String message;

    // Use these fields for specific scenarios
    private Long studentId;              // For a specific student
    private List<Long> studentIds;       // For multiple students
    private Boolean sendToAllStudents;   // For all students

    private Long teacherId;              // For a specific teacher
    private List<Long> teacherIds;       // For multiple teachers
    private Boolean sendToAllTeachers;   // For all teachers
}