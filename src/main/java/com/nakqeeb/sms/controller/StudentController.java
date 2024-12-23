package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.*;
import com.nakqeeb.sms.entity.Grade;
import com.nakqeeb.sms.entity.Student;
import com.nakqeeb.sms.exception.NotFoundException;
import com.nakqeeb.sms.exception.UserAlreadyExistsException;
import com.nakqeeb.sms.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Students", description = "Endpoints for managing students")
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @Operation(
            summary = "Create Student",
            description = "Creates a new student. This operation is restricted to Admin only."
    )
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody @Valid CreateStudentDto createStudentDto) throws UserAlreadyExistsException, NotFoundException {
        Student student = studentService.createStudent(createStudentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @Operation(
            summary = "Update Student",
            description = "Updates an existing student. This operation is restricted to Admin only."
    )
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long studentId,
            @RequestBody @Valid UpdateStudentDto updateStudentDto) throws NotFoundException {
        Student updatedStudent = studentService.updateStudent(studentId, updateStudentDto);
        return ResponseEntity.ok(updatedStudent);
    }

    @Operation(
            summary = "Delete Student",
            description = "Deletes a student. This operation is restricted to Admin only."
    )
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) throws NotFoundException {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Get Student by ID",
            description = "Retrieves a student by ID. This operation is restricted to Admin, Principal, and Teacher."
    )
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) throws NotFoundException {
        Student student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    @Operation(
            summary = "Enroll Student in Class",
            description = "Enrolls a student into a class. This operation is restricted to Admin only"
    )
    @PostMapping("/{studentId}/enroll")
    public ResponseEntity<Student> enrollStudent(
            @PathVariable Long studentId,
            @RequestParam Long classId) throws NotFoundException {
        Student enrolledStudent = studentService.enrollStudent(studentId, classId);
        return ResponseEntity.ok(enrolledStudent);
    }

    @Operation(
            summary = "Track Student Attendance",
            description = "Tracks attendance for a student. This operation is restricted to Teacher only"
    )
    @PostMapping("/{studentId}/attendance")
    public ResponseEntity<AttendanceDto> trackAttendance(
            @PathVariable Long studentId,
            @RequestBody @Valid AttendanceDto attendanceDto) throws NotFoundException {
        attendanceDto.setStudentId(studentId);
        AttendanceDto savedAttendance = studentService.trackAttendance(attendanceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
    }

//    @Operation(
//            summary = "Add Grade to Student",
//            description = "Adds a grade to a student. This operation is restricted to Teacher only"
//    )
//    @PatchMapping("/{studentId}/grades")
//    public ResponseEntity<Grade> addGrade(@PathVariable Long studentId, @RequestBody @Valid Grade grade) throws NotFoundException {
//        Grade addedGrade = studentService.addGrade(studentId, grade);
//        return ResponseEntity.status(HttpStatus.CREATED).body(addedGrade);
//    }
//
//    @Operation(
//            summary = "Get Student Grades",
//            description = "Retrieves all grades of a student. This operation is restricted to Admin, Principal, Teacher, Student and parent."
//    )
//    @GetMapping("/{studentId}/grades")
//    public ResponseEntity<List<Grade>> getGrades(@PathVariable Long studentId) throws NotFoundException {
//        List<Grade> grades = studentService.getGrades(studentId);
//        return ResponseEntity.ok(grades);
//    }
//
//    @Operation(
//            summary = "Update Student Grade",
//            description = "Updates a student's grade. This operation is restricted to Teacher only"
//    )
//    @PutMapping("/{studentId}/grades")
//    public ResponseEntity<Grade> updateGrade(@PathVariable Long studentId, @RequestBody @Valid Grade grade) throws NotFoundException {
//        Grade updatedGrade = studentService.updateGrade(studentId, grade);
//        return ResponseEntity.ok(updatedGrade);
//    }
}