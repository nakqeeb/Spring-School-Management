package com.nakqeeb.sms.dao;

import com.nakqeeb.sms.entity.Exam;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Hidden
public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findBySchoolClassId(Long classId);
    List<Exam> findBySubjectId(Long subjectId);
}