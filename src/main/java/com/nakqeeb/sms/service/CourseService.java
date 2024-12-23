package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.CourseRepository;
import com.nakqeeb.sms.dao.TeacherRepository;
import com.nakqeeb.sms.dto.CreateCourseDto;
import com.nakqeeb.sms.dto.UpdateCourseDto;
import com.nakqeeb.sms.entity.Course;
import com.nakqeeb.sms.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public Course createCourse(CreateCourseDto createCourseDto) {
//        Teacher teacher = teacherRepository.findById(createCourseDto.getTeacherId())
//                .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + createCourseDto.getTeacherId()));

        Course course = new Course();
        course.setCourseName(createCourseDto.getCourseName());
        course.setDescription(createCourseDto.getDescription());
        course.setCredits(createCourseDto.getCredits());
        course.setSyllabusUrl(createCourseDto.getSyllabusUrl());
//        course.setTeacher(teacher);

        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
    }

    public Course updateCourse(Long courseId, UpdateCourseDto updateCourseDto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

        course.setCourseName(updateCourseDto.getCourseName());
        course.setDescription(updateCourseDto.getDescription());
        course.setCredits(updateCourseDto.getCredits());
        course.setSyllabusUrl(updateCourseDto.getSyllabusUrl());

//        if (updateCourseDto.getTeacherId() != null) {
//            Teacher teacher = teacherRepository.findById(updateCourseDto.getTeacherId())
//                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + updateCourseDto.getTeacherId()));
//            course.setTeacher(teacher);
//        }

        return courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new RuntimeException("Course not found with ID: " + courseId);
        }
        courseRepository.deleteById(courseId);
    }
}