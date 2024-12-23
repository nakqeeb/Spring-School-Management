package com.nakqeeb.sms.dto;

import com.nakqeeb.sms.entity.AudienceTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAnnouncementDto {
    @NotEmpty(message = "Title is required.")
    private String title;

    @NotEmpty(message = "Content is required.")
    private String content;

    @NotNull(message = "Audience type is required.")
    private AudienceTypeEnum audienceType;

    @NotNull(message = "Start date is required.")
    private LocalDateTime startDate;

    @NotNull(message = "End date is required.")
    private LocalDateTime endDate;
}