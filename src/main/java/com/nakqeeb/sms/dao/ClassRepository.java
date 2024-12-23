package com.nakqeeb.sms.dao;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nakqeeb.sms.entity.SchoolClass;

import java.util.List;

@Repository
@Hidden
public interface ClassRepository extends JpaRepository<SchoolClass, Long> {
    List<SchoolClass> findByTeacherId(Long teacherId);
    List<SchoolClass> findBySubjectId(Long subjectId);
}
