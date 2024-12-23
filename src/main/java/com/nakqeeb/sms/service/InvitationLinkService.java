package com.nakqeeb.sms.service;

import com.nakqeeb.sms.entity.InvitationLink;
import com.nakqeeb.sms.dao.InvitationLinkRepository;
import com.nakqeeb.sms.entity.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class InvitationLinkService {
    @Autowired
    private InvitationLinkRepository linkRepository;

    public InvitationLink generateInvitationLink(String role) {
//        if (!isValidRole(role)) {
//            throw new IllegalArgumentException("Invalid role. Must be ADMIN, PRINCIPAL, TEACHER, STUDENT or PARENT");
//        }
        String token = UUID.randomUUID().toString();
        InvitationLink link = new InvitationLink();
        link.setToken(token);
        link.setRole(role);
        link.setCreatedAt(LocalDateTime.now());
        link.setExpiresAt(LocalDateTime.now().plusDays(7));  // Expires in 7 days
        link.setUsed(false);

        return linkRepository.save(link);
        // return "http://localhost:8080/auth/signup?token=" + token;
    }

    public InvitationLink validateToken(String token) {
        return linkRepository.findByToken(token)
                .filter(link -> !link.isUsed() && link.getExpiresAt().isAfter(LocalDateTime.now()))
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));
    }

    public void markTokenAsUsed(String token) {
        InvitationLink link = validateToken(token);
        link.setUsed(true);
        linkRepository.save(link);
    }


//    public boolean isValidRole(String role) {
//        try {
//            // Attempt to convert the string to RoleEnum
//            RoleEnum.valueOf(role);
//            return true;  // It's a valid enum value
//        } catch (IllegalArgumentException e) {
//            // If an exception occurs, the value is not valid for RoleEnum
//            return false;
//        }
//    }
}