package com.nakqeeb.sms.dao;

import com.nakqeeb.sms.entity.Course;
import com.nakqeeb.sms.entity.Teacher;
import com.nakqeeb.sms.entity.TeacherSubject;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Hidden
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {
    List<TeacherSubject> findByTeacherId(Long teacherId);
    List<TeacherSubject> findBySubjectId(Long subjectId);
    Optional<TeacherSubject> findByTeacherIdAndSubjectId(Long teacherId, Long subjectId);
    boolean existsByTeacherAndSubject(Teacher teacher, Course subject);
}