package com.nakqeeb.sms.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateExamDto {
    private String examName;
    private Date date;
    private Integer totalMarks;
}