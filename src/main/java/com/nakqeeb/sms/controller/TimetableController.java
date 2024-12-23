package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.*;
import com.nakqeeb.sms.service.TimetableService;
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
@RequestMapping("/api/timetables")
@RequiredArgsConstructor
@Tag(name = "Timetable", description = "Endpoints for managing timetable")
public class TimetableController {

    private final TimetableService timetableService;

    @Operation(
            summary = "Create Timetable",
            description = "Creates a new timetable. This operation is restricted to Admin and Principal."
    )
    @PostMapping
    public ResponseEntity<TimetableResponseDto> createTimetable(@RequestBody TimetableRequestDto requestDto) {
        TimetableResponseDto response = timetableService.createTimetable(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get Timetables by Class",
            description = "Retrieves a list of timetables for a class. This operation is restricted to Admin, Principal, Teacher, Student and Parent."
    )
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<TimetableResponseDto>> getTimetablesByClassId(@PathVariable Long classId) {
        List<TimetableResponseDto> response = timetableService.getTimetablesByClassId(classId);
        return ResponseEntity.ok(response);
    }


    @Operation(
            summary = "Get Timetables by Day",
            description = "Retrieves a list of timetables for a specific day of the week. This operation is restricted to Admin, Principal, Teacher, Student and Parent."
    )
    @GetMapping("/day/{dayOfWeek}")
    public ResponseEntity<List<TimetableResponseDto>> getTimetablesByDayOfWeek(@PathVariable String dayOfWeek) {
        List<TimetableResponseDto> response = timetableService.getTimetablesByDayOfWeek(dayOfWeek);
        return ResponseEntity.ok(response);
    }


    @Operation(
            summary = "Get All Timetables",
            description = "Retrieves a list of all timetables. This operation is restricted to Admin, Principal, Teacher, Student and Parent."
    )
    @GetMapping
    public ResponseEntity<List<TimetableResponseDto>> getAllTimetables() {
        List<TimetableResponseDto> response = timetableService.getAllTimetables();
        return ResponseEntity.ok(response);
    }
}