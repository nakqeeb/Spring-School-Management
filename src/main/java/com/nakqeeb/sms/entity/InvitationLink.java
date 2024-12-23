package com.nakqeeb.sms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class InvitationLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private String role;
    private boolean isUsed;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

}