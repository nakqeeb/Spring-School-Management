package com.nakqeeb.sms.dao;

import com.nakqeeb.sms.entity.Student;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Hidden
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find all students associated with a specific parent
    List<Student> findByParentId(Long parentId);

    // Find student by user ID
    Student findByUserId(Long userId);

//    // Find all students enrolled in a specific class
//    @Query("SELECT s FROM Student s JOIN s.classes c WHERE c.id = :classId")
//    List<Student> findStudentsByClassId(@Param("classId") Long classId);
//
//    // Find students by their assigned course
//    @Query("SELECT s FROM Student s JOIN s.coursesEnrolled c WHERE c.id = :courseId")
//    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);
//
//    // Search students by name
//    List<Student> findByNameContainingIgnoreCase(String name);
}