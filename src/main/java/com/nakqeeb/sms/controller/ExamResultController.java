package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.CreateExamResultDto;
import com.nakqeeb.sms.entity.ExamResult;
import com.nakqeeb.sms.service.ExamResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exam-results")
@RequiredArgsConstructor
@Tag(name = "Exam Results", description = "Endpoints for managing exam results")
public class ExamResultController {

    private final ExamResultService examResultService;

    @Operation(
            summary = "Create Exam Result",
            description = "Creates a new exam result. This operation is restricted to Admin only."
    )
    @PostMapping
    public ResponseEntity<ExamResult> createExamResult(@RequestBody @Valid CreateExamResultDto createExamResultDto) {
        ExamResult examResult = examResultService.createExamResult(createExamResultDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(examResult);
    }

    @Operation(
            summary = "Get Exam Results by Exam ID",
            description = "Retrieves a list of exam results for a specific exam. This operation is restricted to Admin, Principal and Teacher."
    )
    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<ExamResult>> getResultsByExamId(@PathVariable Long examId) {
        List<ExamResult> examResults = examResultService.getExamResultsByExamId(examId);
        return ResponseEntity.ok(examResults);
    }

    @Operation(
            summary = "Get Exam Results by Student ID",
            description = "Retrieves a list of exam results for a specific student. This operation is restricted to Admin, Principal and Teacher."
    )
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ExamResult>> getResultsByStudentId(@PathVariable Long studentId) {
        List<ExamResult> examResults = examResultService.getExamResultsByStudentId(studentId);
        return ResponseEntity.ok(examResults);
    }
}