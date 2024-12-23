package com.nakqeeb.sms.dto;

import com.nakqeeb.sms.entity.PaidStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeeReportDto {
    private Double amount;
    private PaidStatusEnum status;
}