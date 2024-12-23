package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.*;
import com.nakqeeb.sms.dto.CreateExamDto;
import com.nakqeeb.sms.dto.UpdateExamDto;
import com.nakqeeb.sms.entity.*;
import com.nakqeeb.sms.entity.SchoolClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final ClassRepository classRepository;
    private final CourseRepository courseRepository;

    /**
     * Create a new exam.
     */
    public Exam createExam(CreateExamDto createExamDto) {
        SchoolClass schoolClass = classRepository.findById(createExamDto.getClassId())
                .orElseThrow(() -> new IllegalArgumentException("Class not found with ID: " + createExamDto.getClassId()));

        Course subject = courseRepository.findById(createExamDto.getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException("Subject not found with ID: " + createExamDto.getSubjectId()));

        Exam exam = new Exam();
        exam.setExamName(createExamDto.getExamName());
        exam.setSchoolClass(schoolClass);
        exam.setSubject(subject);
        exam.setDate(createExamDto.getDate());
        exam.setTotalMarks(createExamDto.getTotalMarks());

        return examRepository.save(exam);
    }

    /**
     * Update an existing exam.
     */
    public Exam updateExam(Long examId, UpdateExamDto updateExamDto) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("Exam not found with ID: " + examId));

        if (updateExamDto.getExamName() != null) {
            exam.setExamName(updateExamDto.getExamName());
        }
        if (updateExamDto.getDate() != null) {
            exam.setDate(updateExamDto.getDate());
        }
        if (updateExamDto.getTotalMarks() != null) {
            exam.setTotalMarks(updateExamDto.getTotalMarks());
        }

        return examRepository.save(exam);
    }

    /**
     * Get an exam by ID.
     */
    public Exam getExamById(Long examId) {
        return examRepository.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("Exam not found with ID: " + examId));
    }

    /**
     * Get all exams for a class.
     */
    public List<Exam> getExamsByClassId(Long classId) {
        return examRepository.findBySchoolClassId(classId);
    }

    /**
     * Delete an exam.
     */
    public void deleteExam(Long examId) {
        if (!examRepository.existsById(examId)) {
            throw new IllegalArgumentException("Exam not found with ID: " + examId);
        }
        examRepository.deleteById(examId);
    }
}