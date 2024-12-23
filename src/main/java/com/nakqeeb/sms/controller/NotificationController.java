package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.CreateNotificationDto;
import com.nakqeeb.sms.dto.NotificationResponseDto;
import com.nakqeeb.sms.entity.Notification;
import com.nakqeeb.sms.entity.SchoolClass;
import com.nakqeeb.sms.service.AuthenticationService;
import com.nakqeeb.sms.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Notification", description = "Endpoints for managing notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final AuthenticationService authService;

    @Operation(
            summary = "Send Notification",
            description = "Sends a notification to users. This operation is restricted to Admin only."
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<NotificationResponseDto>> sendNotification(@RequestBody @Valid CreateNotificationDto dto) {
        List<Notification> notifications = notificationService.sendNotification(dto);
        List<NotificationResponseDto> responseDtos = notifications.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @Operation(
            summary = "Get All Notifications",
            description = "Retrieves all notifications for all users. This operation is restricted to Admin only."
    )
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<NotificationResponseDto>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        List<NotificationResponseDto> responseDtos = notifications.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @Operation(
            summary = "Get Notification by ID",
            description = "Retrieves a single notification by its ID. This operation is restricted to Admin only."
    )
    @GetMapping("/{notificationId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<NotificationResponseDto> getNotificationById(@PathVariable Long notificationId) {
        Notification notification = notificationService.getNotificationById(notificationId);
        NotificationResponseDto responseDto = mapToDto(notification);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(
            summary = "Get Student Notifications",
            description = "Retrieves notifications for current login student. This operation is restricted to Student only."
    )
    @GetMapping("/student")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<NotificationResponseDto>> getNotificationsForStudent() {
        Long studentId = authService.getCurrentStudentId();
        List<Notification> notifications = notificationService.getNotificationsForStudent(studentId);
        List<NotificationResponseDto> responseDtos = notifications.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @Operation(
            summary = "Get Teacher Notifications",
            description = "Retrieves notifications for current login teacher. This operation is restricted to Teacher only."
    )
    @GetMapping("/teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<List<NotificationResponseDto>> getNotificationsForTeacher() {
        Long teacherId = authService.getCurrentTeacherId();
        List<Notification> notifications = notificationService.getNotificationsForTeacher(teacherId);
        List<NotificationResponseDto> responseDtos = notifications.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @Operation(
            summary = "Delete Notification",
            description = "Deletes a notification. This operation is restricted to Admin only."
    )
    @DeleteMapping("/{notificationId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteNotification(@PathVariable Long notificationId) throws Exception {
        notificationService.deleteNotification(notificationId);
        // Prepare a response message with status
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Notification deleted successfully");
        response.put("status", HttpStatus.OK.value());
        response.put("notificationId", notificationId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Helper method to map Notification to NotificationResponseDto
    private NotificationResponseDto mapToDto(Notification notification) {
        NotificationResponseDto dto = new NotificationResponseDto();
        dto.setId(notification.getId());
        dto.setMessage(notification.getMessage());
        dto.setType(notification.getType().name());
        dto.setCreatedAt(notification.getCreatedAt());
        return dto;
    }
}