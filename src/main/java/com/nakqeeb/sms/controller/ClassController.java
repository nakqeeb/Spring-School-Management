package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.CreateClassDto;
import com.nakqeeb.sms.dto.UpdateClassDto;
import com.nakqeeb.sms.service.ClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nakqeeb.sms.entity.SchoolClass;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
@Tag(name = "Classes", description = "Endpoints for managing classes")
public class ClassController {

    private final ClassService classService;

    @Operation(
            summary = "Create Class",
            description = "Creates a new school class. This operation is restricted to Admin."
    )
    @PostMapping
    public ResponseEntity<SchoolClass> createClass(@RequestBody @Valid CreateClassDto createClassDto) {
        SchoolClass newClass = classService.createClass(createClassDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClass);
    }

    @Operation(
            summary = "Update Class",
            description = "Updates an existing school class. This operation is restricted to Admin."
    )
    @PutMapping("/{classId}")
    public ResponseEntity<SchoolClass> updateClass(
            @PathVariable Long classId,
            @RequestBody @Valid UpdateClassDto updateClassDto) {
        SchoolClass updatedClass = classService.updateClass(classId, updateClassDto);
        return ResponseEntity.ok(updatedClass);
    }

    @Operation(
            summary = "Delete Class",
            description = "Deletes a school class. This operation is restricted to Admin."
    )
    @DeleteMapping("/{classId}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long classId) {
        classService.deleteClass(classId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Get Class by ID",
            description = "Retrieves a single school class by its ID. This operation is restricted to Admin and Principal."
    )
    @GetMapping("/{classId}")
    public ResponseEntity<SchoolClass> getClassById(@PathVariable Long classId) {
        SchoolClass foundClass = classService.getClassById(classId);
        return ResponseEntity.ok(foundClass);
    }

    @Operation(
            summary = "Get All Classes",
            description = "Retrieves a list of all school classes. This operation is restricted to Admin and Principal."
    )
    @GetMapping
    public ResponseEntity<List<SchoolClass>> getAllClasses() {
        List<SchoolClass> classes = classService.getAllClasses();
        return ResponseEntity.ok(classes);
    }

    @Operation(
            summary = "Get Classes by Teacher",
            description = "Retrieves a list of school classes taught by the specified teacher. This operation is restricted to Admin and Principal."
    )
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<SchoolClass>> getClassesByTeacher(@PathVariable Long teacherId) {
        List<SchoolClass> classes = classService.getClassesByTeacherId(teacherId);
        return ResponseEntity.ok(classes);
    }

    /**
     * Get classes by subject ID.
     */
    @Operation(
            summary = "Get Class by Subject",
            description = "Retrieves a list of school classes that belong to the specified subject. This operation is restricted to Admin and Principal."
    )
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<SchoolClass>> getClassesBySubject(@PathVariable Long subjectId) {
        List<SchoolClass> classes = classService.getClassesBySubjectId(subjectId);
        return ResponseEntity.ok(classes);
    }
}