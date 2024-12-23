package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.TeacherSubjectRepository;
import com.nakqeeb.sms.entity.Course;
import com.nakqeeb.sms.entity.Teacher;
import com.nakqeeb.sms.entity.TeacherSubject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherSubjectService {

    private final TeacherSubjectRepository teacherSubjectRepository;

    public TeacherSubject addTeacherSubject(Long teacherId, Long subjectId) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherId); // Assuming teacher exists

        Course course = new Course();
        course.setId(subjectId); // Assuming course exists

        TeacherSubject teacherSubject = new TeacherSubject();
        teacherSubject.setTeacher(teacher);
        teacherSubject.setSubject(course);

        return teacherSubjectRepository.save(teacherSubject);
    }

    public List<TeacherSubject> getSubjectsByTeacher(Long teacherId) {
        return teacherSubjectRepository.findByTeacherId(teacherId);
    }

    public List<TeacherSubject> getTeachersBySubject(Long subjectId) {
        return teacherSubjectRepository.findBySubjectId(subjectId);
    }
}