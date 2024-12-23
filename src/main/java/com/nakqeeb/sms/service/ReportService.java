package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.*;
import com.nakqeeb.sms.dto.AttendanceReportDto;
import com.nakqeeb.sms.dto.ExamResultReportDto;
import com.nakqeeb.sms.dto.FeeReportDto;
import com.nakqeeb.sms.dto.ReportResponseDto;
import com.nakqeeb.sms.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final StudentRepository studentRepository;
    private final FeeRepository feeRepository;
    private final AttendanceRepository attendanceRepository;
    private final ExamResultRepository examResultRepository;

    public ReportResponseDto getStudentReport(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));

        List<Fee> fees = feeRepository.findByStudentId(studentId);
        List<Attendance> attendance = attendanceRepository.findByStudentId(studentId);
        List<ExamResult> examResults = examResultRepository.findByStudentId(studentId);

        return buildReport(student, fees, attendance, examResults);
    }

    public List<ReportResponseDto> getAllReports() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> getStudentReport(student.getId()))
                .collect(Collectors.toList());
    }

    private ReportResponseDto buildReport(Student student, List<Fee> fees, List<Attendance> attendance,
                                          List<ExamResult> examResults) {
        // Map Fee entities to FeeDto
        List<FeeReportDto> feeDtos = fees.stream()
                .map(fee -> new FeeReportDto(fee.getAmount(), fee.getPaidStatus()))
                .collect(Collectors.toList());

        // Map Fee entities to AttendanceReportDto
        List<AttendanceReportDto> attendanceReportDtos = attendance.stream()
                .map(att -> new AttendanceReportDto(att.getSchoolClass().getClassName(), att.getSchoolClass().getClassRoomNumber(), att.getSchoolClass().getSubject().getCourseName(), att.getDate(), att.getStatus()))
                .collect(Collectors.toList());

        // Map Fee entities to ExamResultReportDto
        List<ExamResultReportDto> examResultReportDtos = examResults.stream()
                .map(examResult -> new ExamResultReportDto(examResult.getExam().getExamName(), examResult.getExam().getTotalMarks(), examResult.getMarksObtained(), examResult.getGrade(), examResult.getExam().getDate()))
                .collect(Collectors.toList());

        return new ReportResponseDto(
                student.getId(),
                student.getUser().getName(),
                student.getUser().getEmail(),
                feeDtos,
                attendanceReportDtos,
                examResultReportDtos
        );
    }
}