package com.nakqeeb.sms.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NotificationResponseDto {
    private Long id;
    private String message;
    private String type;
    private Date createdAt;
}