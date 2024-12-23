package com.nakqeeb.sms.dto;

import com.nakqeeb.sms.entity.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExamResultReportDto {
    private String examName;
    private Integer totalMarks;
    private Integer marksObtained;
    private String grade;
    private Date date;
}
