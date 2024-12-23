package com.nakqeeb.sms.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // Set the HTTP response status and message
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);  // 403 Forbidden
        response.setContentType("application/json");
        response.getWriter().write("{\"status\": \"403\", \"message\": \"" + accessDeniedException.getMessage() + "\"}");

    }
}