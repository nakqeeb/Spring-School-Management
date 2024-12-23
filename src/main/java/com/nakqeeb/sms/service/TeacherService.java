package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.CourseRepository;
import com.nakqeeb.sms.dao.TeacherRepository;
import com.nakqeeb.sms.dao.TeacherSubjectRepository;
import com.nakqeeb.sms.dao.UserRepository;
import com.nakqeeb.sms.dto.CreateTeacherDto;
import com.nakqeeb.sms.dto.UpdateTeacherDto;
import com.nakqeeb.sms.entity.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final TeacherSubjectRepository teacherSubjectRepository;
    private final CourseRepository courseRepository;

    /**
     * Create a new teacher.
     */
    public Teacher createTeacher(CreateTeacherDto dto) {
        // Validate subject IDs
//        validateSubjectIds(dto.getSubjectIds());

        // Fetch the user by ID
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dto.getUserId()));


        // Check if the user is already assigned as a teacher
        if (teacherRepository.existsByUser(user)) {
            throw new IllegalStateException("User is already assigned as a teacher.");
        }

        Teacher teacher = new Teacher();
        teacher.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + dto.getUserId())));
        teacher.setQualification(dto.getQualification());

        Teacher savedTeacher = teacherRepository.save(teacher);

        // Assign subjects to teacher
//        for (Long subjectId : dto.getSubjectIds()) {
//            assignSubjectToTeacher(savedTeacher.getId(), subjectId);
//        }

        return savedTeacher;
    }

    /**
     * Update an existing teacher.
     */
    public Teacher updateTeacher(Long teacherId, UpdateTeacherDto dto) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found with ID: " + teacherId));

        if (dto.getQualification() != null) {
            teacher.setQualification(dto.getQualification());
        }

//        if (dto.getSubjectIds() != null) {
//            // Validate subject IDs
//            validateSubjectIds(dto.getSubjectIds());
//
//            List<Long> existingSubjectIds = teacherSubjectRepository.findByTeacherId(teacherId).stream()
//                    .map(ts -> ts.getSubject().getId())
//                    .collect(Collectors.toList());

//            // Assign new subjects
//            for (Long subjectId : dto.getSubjectIds()) {
//                if (!existingSubjectIds.contains(subjectId)) {
//                    assignSubjectToTeacher(teacherId, subjectId);
//                }
//            }
//
//            // Unassign removed subjects
//            for (Long existingSubjectId : existingSubjectIds) {
//                if (!dto.getSubjectIds().contains(existingSubjectId)) {
//                    unassignSubjectFromTeacher(teacherId, existingSubjectId);
//                }
//            }
//        }

        return teacherRepository.save(teacher);
    }

    /**
     * Find teachers by subject taught.
     */
    public List<Teacher> getTeachersBySubject(Long subjectId) {
        return teacherRepository.findTeachersBySubjectId(subjectId);
    }

    /**
     * Find teachers by qualification.
     */
    public List<Teacher> getTeachersByQualification(String qualification) {
        return teacherRepository.findByQualification(qualification);
    }

    /**
     * Get all teachers.
     */
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    /**
     * Get teacher by ID.
     */
    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + teacherId));
    }

    /**
     * Delete a teacher by ID.
     */
    public void deleteTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + teacherId));
        teacherRepository.delete(teacher);
    }

    /**
     * Assign a user to a teacher role.
     */
//    public Teacher assignUserToTeacher(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
//
//        if (teacherRepository.existsByUser(user)) {
//            throw new IllegalStateException("User is already assigned as a teacher.");
//        }
//
//        Teacher teacher = new Teacher();
//        teacher.setUser(user);
//        return teacherRepository.save(teacher);
//    }

    /**
     * Assign a subject to a teacher
     */
    public TeacherSubject assignSubjectToTeacher(Long teacherId, Long subjectId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found with ID: " + teacherId));
        Course course = courseRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + subjectId));

        // Check if the subject is already assigned to the teacher
        boolean alreadyAssigned = teacherSubjectRepository.existsByTeacherAndSubject(teacher, course);
        if (alreadyAssigned) {
            throw new IllegalArgumentException("Subject with ID: " + subjectId + " is already assigned to Teacher ID: " + teacherId);
        }

        TeacherSubject teacherSubject = new TeacherSubject();
        teacherSubject.setTeacher(teacher);
        teacherSubject.setSubject(course);

        return teacherSubjectRepository.save(teacherSubject);
    }

    /**
     * Un-assign a subject from a teacher
     */
    public void unassignSubjectFromTeacher(Long teacherId, Long subjectId) {
        TeacherSubject teacherSubject = teacherSubjectRepository
                .findByTeacherIdAndSubjectId(teacherId, subjectId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Assignment not found for Teacher ID: " + teacherId + " and Subject ID: " + subjectId));

        teacherSubjectRepository.delete(teacherSubject);
    }

    /**
     * Get all subjects assigned to a teacher
     */
    public List<Course> getSubjectsForTeacher(Long teacherId) {
        List<TeacherSubject> teacherSubjects = teacherSubjectRepository.findByTeacherId(teacherId);
        return teacherSubjects.stream().map(TeacherSubject::getSubject).collect(Collectors.toList());
    }

    private void validateSubjectIds(List<Long> subjectIds) {
        List<Long> existingSubjectIds = courseRepository.findExistingSubjectIds(subjectIds);

        for (Long subjectId : subjectIds) {
            if (!existingSubjectIds.contains(subjectId)) {
                throw new IllegalArgumentException("Subject ID " + subjectId + " does not exist in the Course table.");
            }
        }
    }
}