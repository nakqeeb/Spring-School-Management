package com.nakqeeb.sms.dao;

import com.nakqeeb.sms.entity.Timetable;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Hidden
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    List<Timetable> findBySchoolClassId(Long classId);
    List<Timetable> findByDayOfWeek(String dayOfWeek);
}