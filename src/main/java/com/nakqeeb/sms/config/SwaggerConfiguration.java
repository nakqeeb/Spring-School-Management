package com.nakqeeb.sms.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// refer https://www.baeldung.com/spring-boot-swagger-jwt
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("School Management System [SMS]")
                        .description("""
                                The School Management System (SMS) is a comprehensive Spring Boot application designed to streamline academic and administrative tasks. Key features include:
                                
                                - **User Role Management**: Supports Students, Teachers, Admins, and Principals with role-specific functionalities.
                                - **Modules**:
                                    - **User Management**: Handles user registration, authentication, and role-based access.
                                    - **Attendance Tracking**: Record and track attendance for students and teachers.
                                    - **Examination and Grades**: Manage exams, calculate grades, and generate detailed reports.
                                    - **Fee Management**: Track and manage student fee statuses.
                                    - **Notifications**: Send notifications to specific users or groups (students/teachers).
                                    - **Announcements**: Post time-sensitive announcements visible to specific audiences.
                                - **Security**: Implements JWT authentication and role-based access control.
                                - **Swagger Documentation**: Provides a detailed, interactive API interface for developers.
                                """)
                        .version("1.0")
                        .contact(new Contact()
                                .name("Nakqeeb")
                                .email("nakqeeb@gmail.com")
                                .url("https://nakqeeb.github.io/portfolio/"))
                        .license(new License().name("API License")
                                .url("https://example.com/api-license")));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}