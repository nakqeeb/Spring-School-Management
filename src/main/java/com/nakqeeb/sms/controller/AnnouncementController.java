package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.AnnouncementResponseDto;
import com.nakqeeb.sms.dto.CreateAnnouncementDto;
import com.nakqeeb.sms.entity.Announcement;
import com.nakqeeb.sms.entity.AudienceTypeEnum;
import com.nakqeeb.sms.entity.User;
import com.nakqeeb.sms.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
@Tag(name = "Announcement", description = "Endpoints for managing announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @Operation(
            summary = "Create Announcement",
            description = "Creates a new announcement. This operation is restricted to the Admin."
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody @Valid CreateAnnouncementDto dto) {
        Announcement announcement = announcementService.createAnnouncement(dto);
        return new ResponseEntity<>(announcement, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Active Announcements",
            description = "Retrieves a list of active announcements for the currently authenticated user (all user roles)."
    )
    @GetMapping
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN', 'PRINCIPAL')")
    public ResponseEntity<List<AnnouncementResponseDto>> getActiveAnnouncementsForUser(
            @AuthenticationPrincipal User user) {
        String userRole = user.getRole().name();
        System.out.println("User Role: " + userRole);

        AudienceTypeEnum audienceType = determineAudienceType(userRole);
        System.out.println("Determined Audience Type: " + audienceType);

        List<AnnouncementResponseDto> announcements = announcementService.getActiveAnnouncementsForUser(audienceType);
        return ResponseEntity.ok(announcements);
    }

    private AudienceTypeEnum determineAudienceType(String role) {
        // Remove "ROLE_" prefix if it exists
        String cleanRole = role.replace("ROLE_", "").toUpperCase();
        return switch (cleanRole) {
            case "STUDENT" -> AudienceTypeEnum.STUDENTS;
            case "TEACHER" -> AudienceTypeEnum.TEACHERS;
            default -> AudienceTypeEnum.ALL;
        };
    }
}