package com.nakqeeb.sms.dao;

import com.nakqeeb.sms.entity.InvitationLink;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Hidden
public interface InvitationLinkRepository extends JpaRepository<InvitationLink, Long> {
    Optional<InvitationLink> findByToken(String token);
}