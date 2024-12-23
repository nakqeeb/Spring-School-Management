package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.*;
import com.nakqeeb.sms.dto.*;
import com.nakqeeb.sms.entity.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final ClassRepository classRepository;
    private final CourseRepository courseRepository;

    public TimetableResponseDto createTimetable(TimetableRequestDto requestDto) {
        Timetable timetable = new Timetable();

        SchoolClass schoolClass = classRepository.findById(requestDto.getClassId())
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));

        Course subject = courseRepository.findById(requestDto.getSubjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

        timetable.setSchoolClass(schoolClass);
        timetable.setDayOfWeek(requestDto.getDayOfWeek());
        timetable.setTimeSlot(requestDto.getTimeSlot());
        timetable.setSubject(subject);

        Timetable savedTimetable = timetableRepository.save(timetable);

        return mapToResponseDto(savedTimetable);
    }

    public List<TimetableResponseDto> getTimetablesByClassId(Long classId) {
        return timetableRepository.findBySchoolClassId(classId)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public List<TimetableResponseDto> getTimetablesByDayOfWeek(String dayOfWeek) {
        return timetableRepository.findByDayOfWeek(dayOfWeek)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public List<TimetableResponseDto> getAllTimetables() {
        return timetableRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    private TimetableResponseDto mapToResponseDto(Timetable timetable) {
        TimetableResponseDto dto = new TimetableResponseDto();
        dto.setId(timetable.getId());
        dto.setClassId(timetable.getSchoolClass().getId());
        dto.setClassName(timetable.getSchoolClass().getClassName());
        dto.setDayOfWeek(timetable.getDayOfWeek());
        dto.setTimeSlot(timetable.getTimeSlot());
        dto.setSubjectId(timetable.getSubject().getId());
        dto.setSubjectName(timetable.getSubject().getCourseName());
        return dto;
    }
}