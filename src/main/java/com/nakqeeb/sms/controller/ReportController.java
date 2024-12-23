package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.ReportResponseDto;
import com.nakqeeb.sms.service.AuthenticationService;
import com.nakqeeb.sms.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@Tag(name = "Report", description = "Endpoints for managing reports")
public class ReportController {

    private final ReportService reportService;
    private final AuthenticationService authService;

    @GetMapping("/my-report")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ReportResponseDto> getMyReport() {
        Long studentId = authService.getCurrentUserId();
        ReportResponseDto report = reportService.getStudentReport(studentId);
        return ResponseEntity.ok(report);
    }

    @Operation(summary = "Get Student Report",
            description = "Retrieves the report for the specified student ID. " +
                    "Requires the user to have the 'ADMIN', 'PRINCIPAL', or 'TEACHER' role.")
    @GetMapping("/{studentId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PRINCIPAL') or hasRole('TEACHER')")
    public ResponseEntity<ReportResponseDto> getReportByStudentId(@PathVariable Long studentId) {
        ReportResponseDto report = reportService.getStudentReport(studentId);
        return ResponseEntity.ok(report);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PRINCIPAL') or hasRole('TEACHER')")
    public ResponseEntity<List<ReportResponseDto>> getAllReports() {
        List<ReportResponseDto> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }
}