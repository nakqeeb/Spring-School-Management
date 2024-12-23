package com.nakqeeb.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReportResponseDto {
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private List<FeeReportDto> fees;
    private List<AttendanceReportDto> attendance;
    private List<ExamResultReportDto> examResults;
}