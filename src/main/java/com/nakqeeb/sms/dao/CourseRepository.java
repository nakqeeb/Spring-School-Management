package com.nakqeeb.sms.dao;

import com.nakqeeb.sms.entity.Course;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Hidden
public interface CourseRepository extends JpaRepository<Course, Long> {
    /**
     * Finds existing subject IDs from the Course table based on the given list of IDs.
     *
     * @param subjectIds List of subject IDs to check.
     * @return List of subject IDs that exist in the Course table.
     */
    @Query("SELECT c.id FROM Course c WHERE c.id IN :subjectIds")
    List<Long> findExistingSubjectIds(@Param("subjectIds") List<Long> subjectIds);
}