package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.AttendanceRepository;
import com.nakqeeb.sms.dao.ClassRepository;
import com.nakqeeb.sms.dao.StudentRepository;
import com.nakqeeb.sms.dao.UserRepository;
import com.nakqeeb.sms.dto.AttendanceDto;
import com.nakqeeb.sms.dto.CreateStudentDto;
import com.nakqeeb.sms.dto.UpdateStudentDto;
import com.nakqeeb.sms.entity.*;
import com.nakqeeb.sms.exception.NotFoundException;
import com.nakqeeb.sms.exception.UserAlreadyExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.Class;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final ClassRepository classRepository;
    private final AttendanceRepository attendanceRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Create a new student.
     */
    public Student createStudent(CreateStudentDto createStudentDto) throws UserAlreadyExistsException, NotFoundException {
        Optional<User> existUser = this.userRepository.findByEmail(createStudentDto.getEmail());
        if (existUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + existUser.get().getEmail() + " already exists.");
        }
        // Check if the parent exists
        User parent = null;
        if (createStudentDto.getParentId() != null) {
            parent = userRepository.findById(createStudentDto.getParentId())
                    .orElseThrow(() -> new NotFoundException("Parent not found with ID: " + createStudentDto.getParentId()));
            if (!parent.getRole().equals(RoleEnum.PARENT)) {
                throw new IllegalArgumentException("The specified user is not a parent.");
            }
        }

        // Check if the class exists
        SchoolClass schoolClass = null;
        if (createStudentDto.getClassId() != null) {
            schoolClass = classRepository.findById(createStudentDto.getClassId())
                    .orElseThrow(() -> new NotFoundException("Class not found with ID: " + createStudentDto.getClassId()));
        }

        // Create a User for the Student
        User user = new User();
        user.setName(createStudentDto.getName());
        user.setEmail(createStudentDto.getEmail());
        user.setPassword(passwordEncoder.encode(createStudentDto.getPassword()));
        user.setRole(RoleEnum.STUDENT);
        user.setActivated(true);

        userRepository.save(user);

        // Create the Student entity
        Student student = new Student();
        student.setUser(user);
        student.setSchoolClass(schoolClass);
        student.setEnrollmentDate(createStudentDto.getEnrollmentDate());
        student.setParent(parent);

        return studentRepository.save(student);
    }

    /**
     * Update an existing student.
     */
    public Student updateStudent(Long studentId, UpdateStudentDto updateStudentDto) throws NotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + studentId));

        // Update student fields
        if (updateStudentDto.getName() != null) {
            student.getUser().setName(updateStudentDto.getName());
        }
        if (updateStudentDto.getEmail() != null) {
            student.getUser().setEmail(updateStudentDto.getEmail());
        }
//        if (updateStudentDto.getPassword() != null) {
//            student.getUser().setPassword(passwordEncoder.encode(updateStudentDto.getPassword()));
//        }
        if (updateStudentDto.getClassId() != null) {
            SchoolClass schoolClass = classRepository.findById(updateStudentDto.getClassId())
                    .orElseThrow(() -> new NotFoundException("Class not found with ID: " + updateStudentDto.getClassId()));
            student.setSchoolClass(schoolClass);
        }
        if (updateStudentDto.getParentId() != null) {
            User parent = userRepository.findById(updateStudentDto.getParentId())
                    .orElseThrow(() -> new NotFoundException("Parent not found with ID: " + updateStudentDto.getParentId()));
            if (!parent.getRole().equals(RoleEnum.PARENT)) {
                throw new IllegalArgumentException("The specified user is not a parent.");
            }
            student.setParent(parent);
        }

        return studentRepository.save(student);
    }

    /**
     * Delete a student.
     */
    public void deleteStudent(Long studentId) throws NotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + studentId));
        studentRepository.delete(student);
    }

    /**
     * Find a student by ID.
     */
    public Student getStudentById(Long studentId) throws NotFoundException {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + studentId));
    }

    /**
     * Enroll a student into a class.
     */
    public Student enrollStudent(Long studentId, Long classId) throws NotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + studentId));

        SchoolClass schoolClass = classRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException("Class not found with ID: " + classId));

        student.setSchoolClass(schoolClass);
        return studentRepository.save(student);
    }

    /**
     * Track attendance for a student.
     */
    public AttendanceDto trackAttendance(AttendanceDto attendanceDto) throws NotFoundException {
        Student student = studentRepository.findById(attendanceDto.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + attendanceDto.getStudentId()));

        SchoolClass schoolClass = classRepository.findById(attendanceDto.getClassId())
                .orElseThrow(() -> new NotFoundException("Class not found with ID: " + attendanceDto.getClassId()));

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setSchoolClass(schoolClass);
        attendance.setDate(attendanceDto.getDate());
        attendance.setStatus(attendanceDto.getStatus());

        attendanceRepository.save(attendance);

        return attendanceDto;
    }

//    public Grade addGrade(Long studentId, Grade grade) throws NotFoundException {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new NotFoundException("Student not found"));
//
//        student.getGrades().add(grade);
//        studentRepository.save(student);
//        return grade;
//    }
//
//    public List<Grade> getGrades(Long studentId) throws NotFoundException {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new NotFoundException("Student not found"));
//
//        return student.getGrades();
//    }
//
//    public Grade updateGrade(Long studentId, Grade updatedGrade) throws NotFoundException {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new NotFoundException("Student not found"));
//
//        List<Grade> grades = student.getGrades();
//        for (int i = 0; i < grades.size(); i++) {
//            Grade grade = grades.get(i);
//            if (grade.getSubjectName().equals(updatedGrade.getSubjectName()) &&
//                    grade.getGradingDate().equals(updatedGrade.getGradingDate())) {
//                grades.set(i, updatedGrade);
//                student.setGrades(grades);
//                studentRepository.save(student);
//                return updatedGrade;
//            }
//        }
//
//        throw new NotFoundException("Grade not found for the given subject and date.");
//    }
}