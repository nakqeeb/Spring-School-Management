package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.*;
import com.nakqeeb.sms.dto.CreateAttendanceDto;
import com.nakqeeb.sms.dto.UpdateAttendanceDto;
import com.nakqeeb.sms.entity.*;
import com.nakqeeb.sms.entity.SchoolClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;

    /**
     * Create a new attendance record.
     */
    public Attendance createAttendance(CreateAttendanceDto createAttendanceDto) {
        Student student = studentRepository.findById(createAttendanceDto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + createAttendanceDto.getStudentId()));

        SchoolClass schoolClass = classRepository.findById(createAttendanceDto.getClassId())
                .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + createAttendanceDto.getClassId()));

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setSchoolClass(schoolClass);
        attendance.setDate(createAttendanceDto.getDate());
        attendance.setStatus(createAttendanceDto.getStatus());

        return attendanceRepository.save(attendance);
    }

    /**
     * Update an attendance record.
     */
    public Attendance updateAttendance(Long attendanceId, UpdateAttendanceDto updateAttendanceDto) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new IllegalArgumentException("Attendance not found with ID: " + attendanceId));

        if (updateAttendanceDto.getStatus() != null) {
            attendance.setStatus(updateAttendanceDto.getStatus());
        }

        return attendanceRepository.save(attendance);
    }

    /**
     * Get attendance by ID.
     */
    public Attendance getAttendanceById(Long attendanceId) {
        return attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new IllegalArgumentException("Attendance not found with ID: " + attendanceId));
    }

    /**
     * Get attendance records for a specific student.
     */
    public List<Attendance> getAttendanceByStudentId(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    /**
     * Get attendance records for a specific class.
     */
    public List<Attendance> getAttendanceByClassId(Long classId) {
        return attendanceRepository.findBySchoolClassId(classId);
    }

    /**
     * Get attendance for a specific class and date.
     */
    public List<Attendance> getAttendanceByClassAndDate(Long classId, Date date) {
        return attendanceRepository.findBySchoolClassIdAndDate(classId, date);
    }

    /**
     * Get attendance for a specific student and date.
     */
    public List<Attendance> getAttendanceByStudentAndDate(Long studentId, Date date) {
        return attendanceRepository.findByStudentIdAndDate(studentId, date);
    }

    /**
     * Delete an attendance record.
     */
    public void deleteAttendance(Long attendanceId) {
        if (!attendanceRepository.existsById(attendanceId)) {
            throw new IllegalArgumentException("Attendance not found with ID: " + attendanceId);
        }
        attendanceRepository.deleteById(attendanceId);
    }
}