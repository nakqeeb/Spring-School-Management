package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.entity.TeacherSubject;
import com.nakqeeb.sms.service.TeacherSubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teacher-subjects")
@RequiredArgsConstructor
@Tag(name = "Teacher's Subjects", description = "Endpoints for managing teacher's subjects")
public class TeacherSubjectController {

    private final TeacherSubjectService teacherSubjectService;

    @Operation(
            summary = "Assign Subject to Teacher",
            description = "Assigns a subject to a teacher. This operation is restricted to Admin and Principal."
    )
    @PostMapping
    public ResponseEntity<TeacherSubject> assignSubjectToTeacher(
            @RequestParam Long teacherId,
            @RequestParam Long subjectId) {
        TeacherSubject teacherSubject = teacherSubjectService.addTeacherSubject(teacherId, subjectId);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherSubject);
    }


    @Operation(
            summary = "Get Subjects by Teacher",
            description = "Retrieves a list of subjects assigned to a teacher. This operation is restricted to Admin and Principal."
    )
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<TeacherSubject>> getSubjectsByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherSubjectService.getSubjectsByTeacher(teacherId));
    }

    @Operation(
            summary = "Get Teachers by Subject",
            description = "Retrieves a list of teachers assigned to a subject. This operation is restricted to Admin and Principal."
    )
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<TeacherSubject>> getTeachersBySubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(teacherSubjectService.getTeachersBySubject(subjectId));
    }
}