package com.nakqeeb.sms.dao;

import com.nakqeeb.sms.entity.Announcement;
import com.nakqeeb.sms.entity.AudienceTypeEnum;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Hidden
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    @Query("SELECT a FROM Announcement a " +
            "WHERE a.startDate <= :currentDate " +
            "AND a.endDate >= :currentDate " +
            "AND a.audienceType IN (:audienceTypes)")
    List<Announcement> findActiveAnnouncements(
            @Param("currentDate") LocalDateTime currentDate,
            @Param("audienceTypes") List<AudienceTypeEnum> audienceTypes);
}