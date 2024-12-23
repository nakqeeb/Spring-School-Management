package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.CreateCourseDto;
import com.nakqeeb.sms.dto.UpdateCourseDto;
import com.nakqeeb.sms.entity.Course;
import com.nakqeeb.sms.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Courses", description = "Endpoints for managing courses")
@Validated
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @Operation(
            summary = "Create Course",
            description = "Creates a new course. This operation is restricted to Admin and Principal."
    )
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody @Valid CreateCourseDto createCourseDto) {
        Course course = courseService.createCourse(createCourseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @Operation(
            summary = "Get All Courses",
            description = "Retrieves a list of all courses. This operation is restricted to Admin, Principal, Teacher, Student, and Parent."
    )
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @Operation(
            summary = "Get Course by ID",
            description = "Retrieves a course by its ID. This operation is restricted to Admin, Principal, Teacher, Student, and Parent."
    )
    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {
        Course course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }

    @Operation(
            summary = "Update Course",
            description = "Updates an existing course. This operation is restricted to Admin and Principal."
    )
    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long courseId,
            @RequestBody @Valid UpdateCourseDto updateCourseDto) {
        Course updatedCourse = courseService.updateCourse(courseId, updateCourseDto);
        return ResponseEntity.ok(updatedCourse);
    }

    @Operation(
            summary = "Delete Course",
            description = "Deletes a course. This operation is restricted to Admin and Principal."
    )
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}