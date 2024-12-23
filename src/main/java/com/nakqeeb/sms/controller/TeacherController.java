package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.CreateTeacherDto;
import com.nakqeeb.sms.dto.UpdateTeacherDto;
import com.nakqeeb.sms.entity.Teacher;
import com.nakqeeb.sms.entity.TeacherSubject;
import com.nakqeeb.sms.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Teachers", description = "Endpoints for managing teachers")
@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @Operation(
            summary = "Create Teacher",
            description = "Creates a new teacher. This operation is restricted to Admin and Principal."
    )
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody @Valid CreateTeacherDto createTeacherDto) {
        Teacher teacher = teacherService.createTeacher(createTeacherDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
    }


    @Operation(
            summary = "Get Teachers by Subject",
            description = "Retrieves teachers by the subject they teach. This operation is restricted to Admin, Principal, and Teacher."
    )
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<Teacher>> getTeachersBySubject(@PathVariable Long subjectId) {
        List<Teacher> teachers = teacherService.getTeachersBySubject(subjectId);
        return ResponseEntity.ok(teachers);
    }


    @Operation(
            summary = "Get Teachers by Qualification",
            description = "Retrieves a list of teachers with the specified qualification. This operation is restricted to Admin, Principal, and Teacher."
    )
    @GetMapping("/qualification")
    public ResponseEntity<List<Teacher>> getTeachersByQualification(@RequestParam String qualification) {
        List<Teacher> teachers = teacherService.getTeachersByQualification(qualification);
        return ResponseEntity.ok(teachers);
    }


    @Operation(
            summary = "Get All Teachers",
            description = "Retrieves a list of all teachers. This operation is restricted to Admin, Principal, and Teacher."
    )
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }


    @Operation(
            summary = "Get Teacher by ID",
            description = "Retrieves a teacher by their ID. This operation is restricted to Admin, Principal, and Teacher."
    )
    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        return ResponseEntity.ok(teacher);
    }

    /**
     * Update an existing teacher.
     */
    @Operation(
            summary = "Update Teacher",
            description = "Updates an existing teacher. This operation is restricted to Admin and Principal."
    )
    @PutMapping("/{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(
            @PathVariable Long teacherId,
            @RequestBody @Valid UpdateTeacherDto updateTeacherDto) {
        Teacher updatedTeacher = teacherService.updateTeacher(teacherId, updateTeacherDto);
        return ResponseEntity.ok(updatedTeacher);
    }


    @Operation(
            summary = "Delete Teacher",
            description = "Deletes a teacher. This operation is restricted to Admin only."
    )
    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Assign a user to a teacher.
     */
//    @PatchMapping("/assign-user/{userId}")
//    public ResponseEntity<Teacher> assignUserToTeacher(
//            @PathVariable Long userId) {
//        Teacher teacher = teacherService.assignUserToTeacher(userId);
//        return ResponseEntity.ok(teacher);
//    }

    @Operation(
            summary = "Assign Subject to Teacher",
            description = "Assigns a subject to a teacher. This operation is restricted to Admin and Principal."
    )
    @PostMapping("/{teacherId}/assign-subject/{subjectId}")
    public ResponseEntity<TeacherSubject> assignSubjectToTeacher(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId) {
        TeacherSubject assignedSubject = teacherService.assignSubjectToTeacher(teacherId, subjectId);
        return ResponseEntity.ok(assignedSubject);
    }


    @Operation(
            summary = "Unassign Subject from Teacher",
            description = "Unassigns a subject from a teacher. This operation is restricted to Admin and Principal."
    )
    @DeleteMapping("/{teacherId}/unassign-subject/{subjectId}")
    public ResponseEntity<?> unassignSubjectFromTeacher(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId) {
        teacherService.unassignSubjectFromTeacher(teacherId, subjectId);
        // Prepare a response message with status
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Subject unassigned successfully");
        response.put("status", HttpStatus.OK.value());
        response.put("subjectId", subjectId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}