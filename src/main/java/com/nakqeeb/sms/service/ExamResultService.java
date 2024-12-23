package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.*;
import com.nakqeeb.sms.dto.CreateExamResultDto;
import com.nakqeeb.sms.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamResultService {

    private final ExamResultRepository examResultRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    /**
     * Add a new exam result.
     */
    public ExamResult createExamResult(CreateExamResultDto createExamResultDto) {
        Student student = studentRepository.findById(createExamResultDto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + createExamResultDto.getStudentId()));

        Exam exam = examRepository.findById(createExamResultDto.getExamId())
                .orElseThrow(() -> new IllegalArgumentException("Exam not found with ID: " + createExamResultDto.getExamId()));

        ExamResult examResult = new ExamResult();
        examResult.setStudent(student);
        examResult.setExam(exam);
        examResult.setMarksObtained(createExamResultDto.getMarksObtained());
        examResult.setGrade(createExamResultDto.getGrade());

        return examResultRepository.save(examResult);
    }

    /**
     * Get results for a specific exam.
     */
    public List<ExamResult> getExamResultsByExamId(Long examId) {
        return examResultRepository.findByExamId(examId);
    }

    /**
     * Get results for a specific student.
     */
    public List<ExamResult> getExamResultsByStudentId(Long studentId) {
        return examResultRepository.findByStudentId(studentId);
    }
}