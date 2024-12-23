package com.nakqeeb.sms.dto;

import com.nakqeeb.sms.entity.AttStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CreateAttendanceDto {
    @NotNull(message = "Student ID is required.")
    private Long studentId;

    @NotNull(message = "Class ID is required.")
    private Long classId;

    @NotNull(message = "Date is required.")
    private Date date;

    @NotNull(message = "Attendance status is required.")
    private AttStatusEnum status;
}