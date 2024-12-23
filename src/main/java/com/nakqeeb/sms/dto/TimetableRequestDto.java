package com.nakqeeb.sms.dto;

import lombok.Data;

@Data
public class TimetableRequestDto {
    private Long classId;
    private String dayOfWeek;
    private String timeSlot;
    private Long subjectId;
}