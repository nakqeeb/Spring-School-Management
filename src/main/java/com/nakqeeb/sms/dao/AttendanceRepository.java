package com.nakqeeb.sms.dao;

import com.nakqeeb.sms.entity.Attendance;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Hidden
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentId(Long studentId);
    List<Attendance> findBySchoolClassId(Long classId);
    List<Attendance> findBySchoolClassIdAndDate(Long classId, Date date);
    List<Attendance> findByStudentIdAndDate(Long studentId, Date date);
}