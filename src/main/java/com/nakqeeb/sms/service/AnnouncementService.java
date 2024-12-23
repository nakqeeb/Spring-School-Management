package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.AnnouncementRepository;
import com.nakqeeb.sms.dto.AnnouncementResponseDto;
import com.nakqeeb.sms.dto.CreateAnnouncementDto;
import com.nakqeeb.sms.entity.Announcement;
import com.nakqeeb.sms.entity.AudienceTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public Announcement createAnnouncement(CreateAnnouncementDto dto) {
        Announcement announcement = new Announcement();
        announcement.setTitle(dto.getTitle());
        announcement.setContent(dto.getContent());
        announcement.setAudienceType(dto.getAudienceType());
        announcement.setStartDate(dto.getStartDate());
        announcement.setEndDate(dto.getEndDate());

        return announcementRepository.save(announcement);
    }

    public List<AnnouncementResponseDto> getActiveAnnouncementsForUser(AudienceTypeEnum audienceType) {
        LocalDateTime currentDate = LocalDateTime.now();
        System.out.println("Current Date: " + currentDate);

        List<AudienceTypeEnum> audienceTypes = new ArrayList<>();
        audienceTypes.add(AudienceTypeEnum.ALL); // Visible to all
        audienceTypes.add(audienceType); // Visible to specific role

        List<Announcement> announcements = announcementRepository.findActiveAnnouncements(currentDate, audienceTypes);
        System.out.println("Found announcements: " + announcements);

        return announcements.stream()
                .map(a -> new AnnouncementResponseDto(
                        a.getId(),
                        a.getTitle(),
                        a.getContent(),
                        a.getStartDate(),
                        a.getEndDate()))
                .collect(Collectors.toList());
    }
}