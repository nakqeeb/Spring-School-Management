package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.*;
import com.nakqeeb.sms.dto.CreateClassDto;
import com.nakqeeb.sms.dto.UpdateClassDto;
import com.nakqeeb.sms.entity.*;
import com.nakqeeb.sms.entity.SchoolClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    /**
     * Create a new class.
     */
    public SchoolClass createClass(CreateClassDto createClassDto) {
        Teacher teacher = teacherRepository.findById(createClassDto.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found with ID: " + createClassDto.getTeacherId()));

        Course subject = courseRepository.findById(createClassDto.getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + createClassDto.getSubjectId()));

        SchoolClass newClass = new SchoolClass();
        newClass.setClassName(createClassDto.getClassName());
        newClass.setTeacher(teacher);
        newClass.setSubject(subject);
        newClass.setClassRoomNumber(createClassDto.getClassRoomNumber());

        return classRepository.save(newClass);
    }

    /**
     * Update an existing class.
     */
    public SchoolClass updateClass(Long classId, UpdateClassDto updateClassDto) {
        SchoolClass existingClass = classRepository.findById(classId)
                .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + classId));

        if (updateClassDto.getClassName() != null) {
            existingClass.setClassName(updateClassDto.getClassName());
        }

        if (updateClassDto.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(updateClassDto.getTeacherId())
                    .orElseThrow(() -> new IllegalArgumentException("Teacher not found with ID: " + updateClassDto.getTeacherId()));
            existingClass.setTeacher(teacher);
        }

        if (updateClassDto.getSubjectId() != null) {
            Course subject = courseRepository.findById(updateClassDto.getSubjectId())
                    .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + updateClassDto.getSubjectId()));
            existingClass.setSubject(subject);
        }

        if (updateClassDto.getClassRoomNumber() != null) {
            existingClass.setClassRoomNumber(updateClassDto.getClassRoomNumber());
        }

        return classRepository.save(existingClass);
    }

    /**
     * Delete a class.
     */
    public void deleteClass(Long classId) {
        if (!classRepository.existsById(classId)) {
            throw new IllegalArgumentException("Class not found with ID: " + classId);
        }
        classRepository.deleteById(classId);
    }

    /**
     * Get a class by ID.
     */
    public SchoolClass getClassById(Long classId) {
        return classRepository.findById(classId)
                .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + classId));
    }

    /**
     * Get all classes.
     */
    public List<SchoolClass> getAllClasses() {
        return classRepository.findAll();
    }

    /**
     * Get all classes taught by a specific teacher.
     */
    public List<SchoolClass> getClassesByTeacherId(Long teacherId) {
        return classRepository.findByTeacherId(teacherId);
    }

    /**
     * Get all classes for a specific subject.
     */
    public List<SchoolClass> getClassesBySubjectId(Long subjectId) {
        return classRepository.findBySubjectId(subjectId);
    }
}