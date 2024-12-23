package com.nakqeeb.sms.dto;

import com.nakqeeb.sms.entity.AttStatusEnum;
import com.nakqeeb.sms.entity.SchoolClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AttendanceReportDto {
    private String className;
    private String classRoomNumber;
    private String subjectName;
    private Date date;
    private AttStatusEnum status;
}
