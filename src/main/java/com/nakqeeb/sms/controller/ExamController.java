package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.CreateExamDto;
import com.nakqeeb.sms.dto.UpdateExamDto;
import com.nakqeeb.sms.entity.Exam;
import com.nakqeeb.sms.service.ExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
@Tag(name = "Exams", description = "Endpoints for managing exams")
public class ExamController {

    private final ExamService examService;

    @Operation(
            summary = "Create Exam",
            description = "Creates a new exam. This operation is restricted to Admin and Principal."
    )
    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody @Valid CreateExamDto createExamDto) {
        Exam exam = examService.createExam(createExamDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(exam);
    }

    @Operation(
            summary = "Update Exam",
            description = "Updates an existing exam. This operation is restricted to Admin and Principal."
    )
    @PatchMapping("/{examId}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long examId, @RequestBody @Valid UpdateExamDto updateExamDto) {
        Exam updatedExam = examService.updateExam(examId, updateExamDto);
        return ResponseEntity.ok(updatedExam);
    }

    @Operation(
            summary = "Get Exam by ID",
            description = "Retrieves an exam by its ID. This operation is restricted to Admin, Principal and Teacher."
    )
    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long examId) {
        Exam exam = examService.getExamById(examId);
        return ResponseEntity.ok(exam);
    }

    @Operation(
            summary = "Delete Exam",
            description = "Deletes an exam. This operation is restricted to Admin only."
    )
    @DeleteMapping("/{examId}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long examId) {
        examService.deleteExam(examId);
        return ResponseEntity.noContent().build();
    }
}