package com.nakqeeb.sms.dao;

import com.nakqeeb.sms.entity.Teacher;
import com.nakqeeb.sms.entity.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//@Hidden
//public interface TeacherRepository extends JpaRepository<Teacher, Long> {
//
//    // Find all teachers associated with a specific subject
//    List<Teacher> findBySubjectsTaughtContaining(String subject);
//
////    // Find teachers by their assigned class ID
////    @Query("SELECT t FROM Teacher t JOIN t.classes c WHERE c.id = :classId")
////    List<Teacher> findTeachersByClassId(@Param("classId") Long classId);
////
////    // Find teachers by their assigned course ID
////    @Query("SELECT t FROM Teacher t JOIN t.coursesAssigned c WHERE c.id = :courseId")
////    List<Teacher> findTeachersByCourseId(@Param("courseId") Long courseId);
//}
//

//@Repository
//@Hidden
//public interface TeacherRepository extends JpaRepository<Teacher, Long> {
//
//    // Find teachers by a specific subject
//    List<Teacher> findBySubjectsTaughtContaining(String subject);
//
//    // Find teachers by qualification
//    List<Teacher> findByQualification(String qualification);
//
//    boolean existsByUser(User user);
//}

@Repository
@Hidden
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    // Find teachers by a specific subject ID
    @Query("SELECT t FROM Teacher t JOIN t.teacherSubjects ts WHERE ts.subject.id = :subjectId")
    List<Teacher> findTeachersBySubjectId(@Param("subjectId") Long subjectId);

    // Find teachers by qualification
    List<Teacher> findByQualification(String qualification);

    // Check if a teacher exists by user
    boolean existsByUser(User user);

    // Find teacher by user ID
    Teacher findByUserId(Long userId);

}