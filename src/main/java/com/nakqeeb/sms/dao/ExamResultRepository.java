package com.nakqeeb.sms.dao;

import com.nakqeeb.sms.entity.ExamResult;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Hidden
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
    List<ExamResult> findByStudentId(Long studentId);
    List<ExamResult> findByExamId(Long examId);
    List<ExamResult> findByExamIdAndStudentId(Long examId, Long studentId);
}