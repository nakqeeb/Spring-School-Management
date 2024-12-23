package com.nakqeeb.sms.dao;

import com.nakqeeb.sms.entity.RoleEnum;
import com.nakqeeb.sms.entity.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Hidden
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    public Page<User> findByRole(RoleEnum role, Pageable pageable);
}