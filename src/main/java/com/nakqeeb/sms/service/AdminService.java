package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.StudentRepository;
import com.nakqeeb.sms.dao.TeacherRepository;
import com.nakqeeb.sms.dao.UserRepository;
import com.nakqeeb.sms.dto.ActivateUserDto;
import com.nakqeeb.sms.entity.RoleEnum;
import com.nakqeeb.sms.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class AdminService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public AdminService(UserRepository userRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return user.get();
    }

    public void activateUser(Long id, ActivateUserDto activateUserDto) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty() || Objects.equals(user.get().getEmail(), "admin@schoolsms.com")) {
            throw new UsernameNotFoundException("User not found");
        }
        user.get().setActivated(activateUserDto.isActivated());

        userRepository.save(user.get());
    }

    public long countStudents() {
        return studentRepository.count();
    }

    public long countTeachers() {
        return teacherRepository.count();
    }
}
