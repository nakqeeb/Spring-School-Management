package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.CreateAttendanceDto;
import com.nakqeeb.sms.dto.UpdateAttendanceDto;
import com.nakqeeb.sms.entity.Attendance;
import com.nakqeeb.sms.service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
@Tag(name = "Attendance", description = "Endpoints for managing attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Operation(
            summary = "Create Attendance Record",
            description = "Creates a new attendance record. This operation is restricted to Admin and Teacher."
    )
    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@RequestBody @Valid CreateAttendanceDto createAttendanceDto) {
        Attendance attendance = attendanceService.createAttendance(createAttendanceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(attendance);
    }

    @Operation(
            summary = "Update Attendance Record",
            description = "Updates an existing attendance record. This operation is restricted to Admin and Teacher."
    )
    @PatchMapping("/{attendanceId}")
    public ResponseEntity<Attendance> updateAttendance(
            @PathVariable Long attendanceId,
            @RequestBody @Valid UpdateAttendanceDto updateAttendanceDto) {
        Attendance updatedAttendance = attendanceService.updateAttendance(attendanceId, updateAttendanceDto);
        return ResponseEntity.ok(updatedAttendance);
    }

    @Operation(
            summary = "Get Attendance by ID",
            description = "Retrieves an attendance record by its ID. This operation is restricted to Admin and Teacher."
    )
    @GetMapping("/{attendanceId}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long attendanceId) {
        Attendance attendance = attendanceService.getAttendanceById(attendanceId);
        return ResponseEntity.ok(attendance);
    }

    @Operation(
            summary = "Get Attendance by Student ID",
            description = "Retrieves a list of attendance records for the specified student ID. This operation is restricted to Admin and Teacher."
    )
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getAttendanceByStudentId(@PathVariable Long studentId) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByStudentId(studentId);
        return ResponseEntity.ok(attendanceList);
    }

    @Operation(
            summary = "Get Attendance by Class ID",
            description = "Retrieves a list of attendance records for the specified class ID. This operation is restricted to Admin and Teacher."
    )
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<Attendance>> getAttendanceByClassId(@PathVariable Long classId) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByClassId(classId);
        return ResponseEntity.ok(attendanceList);
    }

    @Operation(
            summary = "Get Attendance by Class and Date",
            description = "Retrieves a list of attendance records for the specified class and date. This operation is restricted to Admin and Teacher."
    )
    @GetMapping("/class/{classId}/date/{date}")
    public ResponseEntity<List<Attendance>> getAttendanceByClassAndDate(
            @PathVariable Long classId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByClassAndDate(classId, date);
        return ResponseEntity.ok(attendanceList);
    }

    @Operation(
            summary = "Delete Attendance Record",
            description = "Deletes an attendance record by its ID. This operation is restricted to Admin only."
    )
    @DeleteMapping("/{attendanceId}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
        return ResponseEntity.noContent().build();
    }
}