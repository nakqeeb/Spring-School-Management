package com.nakqeeb.sms.config;

import com.nakqeeb.sms.exception.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//@EnableAspectJAutoProxy
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/principal/**").hasAnyRole("PRINCIPAL", "ADMIN")
                        // .requestMatchers("/api/teachers/**").hasAnyRole("ADMIN", "PRINCIPAL")
                        // .requestMatchers("/api/students/**").hasRole("ADMIN")
                        .requestMatchers("/api/parents/**").hasRole("PARENT")
                        // Student API endpoints
                        .requestMatchers(HttpMethod.POST, "/api/students").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/students/{studentId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/students/{studentId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/students/{studentId}").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/students").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER")
                        .requestMatchers(HttpMethod.PATCH, "/api/students/{studentId}/enroll/{classId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/students/{studentId}/attendance").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.PATCH, "/api/students/{studentId}/grades").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/students/{studentId}/grades").hasAnyRole("TEACHER", "PARENT", "STUDENT", "ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.PUT, "/api/students/{studentId}/grades").hasRole("TEACHER")
                        // Teacher API Role Assignments
                        .requestMatchers(HttpMethod.POST, "/api/teachers").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.GET, "/api/teachers").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/teachers/{teacherId}").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER")
                        .requestMatchers(HttpMethod.PUT, "/api/teachers/{teacherId}").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.DELETE, "/api/teachers/{teacherId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/teachers/{teacherId}/assign-subject/{subjectId}").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.PATCH, "/api/teachers/{teacherId}/unassign-subject/{subjectId}").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.GET, "/api/teachers/qualification").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/teachers/subject/{subjectId}").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER")
                        // Course API Role Assignments
                        .requestMatchers(HttpMethod.POST, "/api/courses").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.GET, "/api/courses").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER", "STUDENT", "PARENT")
                        .requestMatchers(HttpMethod.GET, "/api/courses/{courseId}").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER", "STUDENT", "PARENT")
                        .requestMatchers(HttpMethod.PUT, "/api/courses/**").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.DELETE, "/api/courses/**").hasAnyRole("ADMIN", "PRINCIPAL")
                        // Class API Role Assignments
                        .requestMatchers(HttpMethod.POST, "/api/classes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/classes/{classId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/classes/{classId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/classes/{classId}").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.GET, "/api/classes").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.GET, "/api/classes/teacher/{teacherId}").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.GET, "/api/classes/subject/{subjectId}").hasAnyRole("ADMIN", "PRINCIPAL")
                        // Attendance API Role Assignments
                        .requestMatchers(HttpMethod.POST, "/api/attendance").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.PATCH, "/api/attendance/{attendanceId}").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/attendance/{attendanceId}").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/attendance/student/{studentId}").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/attendance/class/{classId}").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/attendance/class/{classId}/date/{date}").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/api/attendance/{attendanceId}").hasRole("ADMIN")
                        // Exam API Role Assignments
                        .requestMatchers(HttpMethod.POST, "/api/exams").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.PATCH, "/api/exams/{examId}").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.GET, "/api/exams/{examId}").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/api/exams/{examId}").hasRole("ADMIN")
                        // ExamResult API Role Assignments
                        .requestMatchers(HttpMethod.POST, "/api/exam-results").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/exam-results/exam/{examId}").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/exam-results/student/{studentId}").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER")
                        // Fee API Role Assignments
                        .requestMatchers(HttpMethod.POST, "/api/fees").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/fees/{feeId}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/fees/student/{studentId}").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.GET, "/api/fees/status/{status}").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.DELETE, "/api/fees/{feeId}").hasAnyRole("ADMIN")
                        // Timetable API Role Assignments
                        .requestMatchers(HttpMethod.POST, "/api/timetables").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.GET, "/api/timetables/class/{classId}").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER", "STUDENT", "PARENT")
                        .requestMatchers(HttpMethod.GET, "/api/timetables/day/{dayOfWeek}").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER", "STUDENT", "PARENT")
                        .requestMatchers(HttpMethod.GET, "/api/timetables").hasAnyRole("ADMIN", "PRINCIPAL", "TEACHER", "STUDENT", "PARENT")
                        // TeacherSubject API Role Assignments
                        .requestMatchers(HttpMethod.POST, "/api/teacher-subjects").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.GET, "/api/teacher-subjects/teacher/{teacherId}").hasAnyRole("ADMIN", "PRINCIPAL")
                        .requestMatchers(HttpMethod.GET, "/api/teacher-subjects/subject/{subjectId}").hasAnyRole("ADMIN", "PRINCIPAL")


                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedHandler(new CustomAccessDeniedHandler())  // Custom Access Denied Handler
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless sessions
                )
                .authenticationProvider(authenticationProvider)  // Custom AuthenticationProvider
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // JWT Filter before UsernamePasswordAuthenticationFilter
                .build();

    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable())  // Disable CSRF
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/**").permitAll()  // Allow public access to authentication paths
//                        .requestMatchers("api/posts/approved").permitAll()
//                        .requestMatchers(HttpMethod.GET, "api/posts/approved/{id}").permitAll()
//                        // https://stackoverflow.com/a/75237203
//                        .requestMatchers(HttpMethod.GET, "api/posts/search/findByUserId", "/error").hasAnyRole("ADMIN", "SUPER_ADMIN")
//                        // .requestMatchers("api-docs/**", "swagger-ui/**").permitAll() // Permit Swagger path << this is one approach. Another approach, I did it in webSecurityCustomizer method
//                        .anyRequest().authenticated()  // All other requests require authentication
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless sessions
//                )
//                .authenticationProvider(authenticationProvider)  // Custom AuthenticationProvider
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // JWT Filter before UsernamePasswordAuthenticationFilter
//                .build();  // Build the security chain
//    }

    // Permit Swagger path
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/swagger-ui/**", "/v3/api-docs/**", "api-docs/**"
        );
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8005"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}