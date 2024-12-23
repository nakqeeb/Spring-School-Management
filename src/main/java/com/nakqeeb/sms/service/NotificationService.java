package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.*;
import com.nakqeeb.sms.dto.CreateNotificationDto;
import com.nakqeeb.sms.entity.*;
import com.nakqeeb.sms.exception.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public List<Notification> sendNotification(CreateNotificationDto dto) {
        List<Notification> notifications = new ArrayList<>();

        if (dto.getStudentId() != null) {
            // Send notification to a specific student
            Student student = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + dto.getStudentId()));
            notifications.add(createNotificationForStudent(dto.getMessage(), student));
        } else if (dto.getStudentIds() != null && !dto.getStudentIds().isEmpty()) {
            // Send notifications to multiple students
            List<Student> students = studentRepository.findAllById(dto.getStudentIds());
            students.forEach(student ->
                    notifications.add(createNotificationForStudent(dto.getMessage(), student))
            );
        } else if (dto.getSendToAllStudents() != null && dto.getSendToAllStudents()) {
            // Send notifications to all students
            List<Student> students = studentRepository.findAll();
            students.forEach(student ->
                    notifications.add(createNotificationForStudent(dto.getMessage(), student))
            );
        } else if (dto.getTeacherId() != null) {
            // Send notification to a specific teacher
            Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new IllegalArgumentException("Teacher not found with ID: " + dto.getTeacherId()));
            notifications.add(createNotificationForTeacher(dto.getMessage(), teacher));
        } else if (dto.getTeacherIds() != null && !dto.getTeacherIds().isEmpty()) {
            // Send notifications to multiple teachers
            List<Teacher> teachers = teacherRepository.findAllById(dto.getTeacherIds());
            teachers.forEach(teacher ->
                    notifications.add(createNotificationForTeacher(dto.getMessage(), teacher))
            );
        } else if (dto.getSendToAllTeachers() != null && dto.getSendToAllTeachers()) {
            // Send notifications to all teachers
            List<Teacher> teachers = teacherRepository.findAll();
            teachers.forEach(teacher ->
                    notifications.add(createNotificationForTeacher(dto.getMessage(), teacher))
            );
        } else {
            throw new IllegalArgumentException("Invalid notification parameters provided.");
        }

        return notificationRepository.saveAll(notifications);
    }

    private Notification createNotificationForStudent(String message, Student student) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setCreatedAt(new Date());
        notification.setStudent(student);
        notification.setType(NotificationType.STUDENT);
        return notification;
    }

    private Notification createNotificationForTeacher(String message, Teacher teacher) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setCreatedAt(new Date());
        notification.setTeacher(teacher);
        notification.setType(NotificationType.TEACHER);
        return notification;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        if (notification.isEmpty()) {
            throw new EntityNotFoundException("No notification found");
        }

        return notification.get();
    }

    public List<Notification> getNotificationsForStudent(Long studentId) {
        return notificationRepository.findByStudentIdOrderByCreatedAtDesc(studentId);
    }

    public List<Notification> getNotificationsForTeacher(Long teacherId) {
        return notificationRepository.findByTeacherIdOrderByCreatedAtDesc(teacherId);
    }

    public void deleteNotification(Long notificationId) throws Exception {
        notificationRepository.deleteById(notificationId);
        Optional<Notification> notification = notificationRepository.findById(notificationId);

        if (notification.isEmpty()) {
            throw new NotFoundException("Notification with id " + notificationId + " does not exist");
        }

        notificationRepository.deleteById(notificationId);
    }

}