package com.nakqeeb.sms.dao;

import com.nakqeeb.sms.entity.Notification;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Hidden
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Find notifications for a specific student
    List<Notification> findByStudentIdOrderByCreatedAtDesc(Long studentId);

    // Find notifications for a specific teacher
    List<Notification> findByTeacherIdOrderByCreatedAtDesc(Long teacherId);
}