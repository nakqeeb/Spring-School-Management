package com.nakqeeb.sms.dto;

import lombok.Data;

@Data
public class UpdateCourseDto {
    private String courseName;
    private String description;
//    private Long teacherId;
    private Integer credits;
    private String syllabusUrl;
}