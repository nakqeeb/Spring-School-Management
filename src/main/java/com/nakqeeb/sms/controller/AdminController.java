package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.ActivateUserDto;
import com.nakqeeb.sms.entity.RoleEnum;
import com.nakqeeb.sms.entity.User;
import com.nakqeeb.sms.exception.ErrorMapper;
import com.nakqeeb.sms.service.AdminService;
import com.nakqeeb.sms.service.InvitationLinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Admin", description = "Endpoints for managing admin stuff")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final ErrorMapper errorMapper;
    private final InvitationLinkService invitationLinkService;

    public AdminController(AdminService adminService, ErrorMapper errorMapper, InvitationLinkService invitationLinkService) {
        this.adminService = adminService;
        this.errorMapper = errorMapper;
        this.invitationLinkService = invitationLinkService;
    }

    @Operation(
            summary = "Activate/Deactivate User",
            description = "Activates or deactivates a user based on the provided request. This operation is restricted to the Admin."
    )
    @PutMapping("/users/activate/{id}")
    public ResponseEntity<?> activateUser(@PathVariable String id, @RequestBody @Valid ActivateUserDto activateUserDto) {

        try {
            this.adminService.activateUser(Long.parseLong(id), activateUserDto);
            // Prepare a response message with status
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User " + (activateUserDto.isActivated() ? "activated" : "deactivated") + " successfully");
            response.put("status", HttpStatus.OK.value());
            response.put("activated", activateUserDto.isActivated());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(this.errorMapper.createErrorMap(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Generate User Invitation Link.",
            description = "Generates an invitation link for a new user. This operation is restricted to the Admin."
    )
    @PostMapping("/generate-invitation")
    public ResponseEntity<?> generateInvitation(@RequestParam String role) {
        if (isValidRole(role.toUpperCase())) {
            return new ResponseEntity<>(invitationLinkService.generateInvitationLink(role.toUpperCase()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(this.errorMapper.createErrorMap("Invalid role. Must be ADMIN, PRINCIPAL, TEACHER, STUDENT or PARENT"), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Get User by Email",
            description = "Retrieves a single user by their email address. This operation is restricted to the Admin."
    )
    @GetMapping("/users/email")
    public ResponseEntity<?> findUserByEmail(@RequestParam(name = "email") String email) {
        try {
            User user = adminService.findUserByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(this.errorMapper.createErrorMap(e), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Get All Users",
            description = "Retrieves a list of all registered users. This operation is restricted to the Admin."
    )
    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers() {
        try {
            return new ResponseEntity<>(adminService.findAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(this.errorMapper.createErrorMap(e), HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isValidRole(String role) {
        try {
            RoleEnum.valueOf(role);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
