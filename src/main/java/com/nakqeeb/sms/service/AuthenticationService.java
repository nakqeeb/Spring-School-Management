package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.StudentRepository;
import com.nakqeeb.sms.dao.TeacherRepository;
import com.nakqeeb.sms.dao.UserRepository;
import com.nakqeeb.sms.dto.LoginUserDto;
import com.nakqeeb.sms.dto.RegisterUserDto;
import com.nakqeeb.sms.entity.RoleEnum;
import com.nakqeeb.sms.entity.Student;
import com.nakqeeb.sms.entity.Teacher;
import com.nakqeeb.sms.entity.User;
import com.nakqeeb.sms.exception.InvalidCredentialException;
import com.nakqeeb.sms.exception.UserAlreadyExistsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository, TeacherRepository teacherRepository, StudentRepository studentRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input, String role) throws UserAlreadyExistsException {
        Optional<User> existUser = this.userRepository.findByEmail(input.getEmail());
        if (existUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + existUser.get().getEmail() + " already exists.");
        }

        var user = new User();
                user.setName(input.getName());
                user.setEmail(input.getEmail());
                user.setPassword(passwordEncoder.encode(input.getPassword()));
                user.setRole(RoleEnum.valueOf(role));
                user.setContactNumber(input.getContactNumber());
                user.setAddress(input.getAddress());
                user.setDateOfBirth(input.getDateOfBirth());
                user.setProfilePicture(input.getProfilePicture());
                user.setActivated(false);
        user = userRepository.save(user); // Save the user first

        if (role.equalsIgnoreCase("STUDENT")) {
            Student student = new Student();
            student.setUser(user);
            student.setEnrollmentDate(user.getCreatedAt());

            studentRepository.save(student);

        } else if (role.equalsIgnoreCase("TEACHER")) {
            Teacher teacher = new Teacher();
            teacher.setUser(user);

            teacherRepository.save(teacher);
        }

        return user;
    }

    public User authenticate(LoginUserDto input) throws Exception {

        Optional<User> user = this.userRepository.findByEmail(input.getEmail());

        if (user.isEmpty()) {
            throw new InvalidCredentialException("Invalid Credentials");
        } else if (!user.get().isActivated()) {
            throw new Exception("Your account hasn't been activated yet");
        }
//        if (!this.userService.isUserExist(input.getEmail())) {
//            throw new InvalidCredentialException("Invalid Credentials");
//        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return this.userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    public Long getCurrentUserId() {
        // Get the currently authenticated principal from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }

        if (authentication.getPrincipal() instanceof User user) {
            return user.getId();
        }

        throw new IllegalStateException("Unable to retrieve user information from authentication context");
    }

    public Long getCurrentStudentId() {
        // Get the currently authenticated principal from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }

        if (authentication.getPrincipal() instanceof User user) {
            Student student = studentRepository.findByUserId(user.getId());
            return student.getId();
        }

        throw new IllegalStateException("Unable to retrieve user information from authentication context");
    }

    public Long getCurrentTeacherId() {
        // Get the currently authenticated principal from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }

        if (authentication.getPrincipal() instanceof User user) {
            Teacher teacher = teacherRepository.findByUserId(user.getId());
            return teacher.getId();
        }

        throw new IllegalStateException("Unable to retrieve user information from authentication context");
    }
}