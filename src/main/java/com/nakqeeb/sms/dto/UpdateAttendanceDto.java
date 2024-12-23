package com.nakqeeb.sms.dto;

import com.nakqeeb.sms.entity.AttStatusEnum;
import lombok.Data;

@Data
public class UpdateAttendanceDto {
    private AttStatusEnum status;
}