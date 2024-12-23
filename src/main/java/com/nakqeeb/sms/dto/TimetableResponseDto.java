package com.nakqeeb.sms.dto;

import lombok.Data;

@Data
public class TimetableResponseDto {
    private Long id;
    private Long classId;
    private String className;
    private String dayOfWeek;
    private String timeSlot;
    private Long subjectId;
    private String subjectName;
}