package com.nakqeeb.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountResponseDto {
    private long studentCount;
    private long teacherCount;
}
