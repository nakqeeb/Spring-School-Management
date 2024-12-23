package com.nakqeeb.sms.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FeeResponseDto {
    private Long id;
    private Long studentId;
    private Double amount;
    private Date dueDate;
    private String paidStatus;
    private Date paymentDate;
}