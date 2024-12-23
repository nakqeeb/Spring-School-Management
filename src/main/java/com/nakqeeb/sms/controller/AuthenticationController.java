package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.LoginUserDto;
import com.nakqeeb.sms.dto.RegisterUserDto;
import com.nakqeeb.sms.entity.InvitationLink;
import com.nakqeeb.sms.entity.User;
import com.nakqeeb.sms.response.LoginResponse;
import com.nakqeeb.sms.service.AuthenticationService;
import com.nakqeeb.sms.service.InvitationLinkService;
import com.nakqeeb.sms.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Auth", description = "Endpoints for managing authentication")
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final InvitationLinkService invitationLinkService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, InvitationLinkService invitationLinkService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.invitationLinkService = invitationLinkService;
    }

    @Operation(
            summary = "Register User",
            description = "Registers a new user account."
    )
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestParam String token, @Valid @RequestBody RegisterUserDto registerUserDto, BindingResult result) {
        try {
            InvitationLink invitation = invitationLinkService.validateToken(token);
            // System.out.println("This invitation token: " + invitation);
            if (result.hasErrors()) {
                return ResponseEntity.badRequest().body(result.getAllErrors());
            }
            User registeredUser = authenticationService.signup(registerUserDto, invitation.getRole());
            invitationLinkService.markTokenAsUsed(token);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (Exception e) {
            // Prepare a response message with status
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status",  HttpStatus.CONFLICT.value());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @Operation(
            summary = "Login User",
            description = "Logs in an existing and activated user."
    )
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return ResponseEntity.badRequest().body(result.getAllErrors());
            }
            User authenticatedUser = authenticationService.authenticate(loginUserDto);

            String jwtToken = jwtService.generateToken(authenticatedUser);

            LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            // Prepare a response message with status
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("status",  HttpStatus.UNAUTHORIZED.value());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

        }

    }
}