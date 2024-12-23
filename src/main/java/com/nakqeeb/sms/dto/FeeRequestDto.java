package com.nakqeeb.sms.dto;

import com.nakqeeb.sms.entity.PaidStatusEnum;
import lombok.Data;

import java.util.Date;

@Data
public class FeeRequestDto {
    private Long studentId;
    private Double amount;
    private Date dueDate;
    private PaidStatusEnum paidStatus;
    private Date paymentDate;
}