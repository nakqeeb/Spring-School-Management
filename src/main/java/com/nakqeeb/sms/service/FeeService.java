package com.nakqeeb.sms.service;

import com.nakqeeb.sms.dao.FeeRepository;
import com.nakqeeb.sms.dao.StudentRepository;
import com.nakqeeb.sms.dto.FeeRequestDto;
import com.nakqeeb.sms.dto.FeeResponseDto;
import com.nakqeeb.sms.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeeService {

    private final FeeRepository feeRepository;
    private final StudentRepository studentRepository;

    public FeeResponseDto createFee(FeeRequestDto feeRequestDto) {
        Student student = studentRepository.findById(feeRequestDto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Fee fee = new Fee();
        fee.setStudent(student);
        fee.setAmount(feeRequestDto.getAmount());
        fee.setDueDate(feeRequestDto.getDueDate());
        fee.setPaidStatus(feeRequestDto.getPaidStatus());
        fee.setPaymentDate(feeRequestDto.getPaymentDate());

        Fee savedFee = feeRepository.save(fee);
        return mapToDto(savedFee);
    }

    public FeeResponseDto updateFee(Long feeId, FeeRequestDto feeRequestDto) {
        Fee fee = feeRepository.findById(feeId)
                .orElseThrow(() -> new IllegalArgumentException("Fee not found"));

        Student student = studentRepository.findById(feeRequestDto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        fee.setStudent(student);
        fee.setAmount(feeRequestDto.getAmount());
        fee.setDueDate(feeRequestDto.getDueDate());
        fee.setPaidStatus(feeRequestDto.getPaidStatus());
        fee.setPaymentDate(feeRequestDto.getPaymentDate());

        Fee updatedFee = feeRepository.save(fee);
        return mapToDto(updatedFee);
    }

    public List<FeeResponseDto> getFeesByStudent(Long studentId) {
        return feeRepository.findByStudentId(studentId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<FeeResponseDto> getFeesByStatus(String status) {
        PaidStatusEnum paidStatus = PaidStatusEnum.valueOf(status.toUpperCase());
        // PaidStatusEnum paidStatus = PaidStatusEnum.fromString(status.toString());
        return feeRepository.findByPaidStatus(paidStatus).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public void deleteFee(Long feeId) {
        feeRepository.deleteById(feeId);
    }

    private FeeResponseDto mapToDto(Fee fee) {
        FeeResponseDto dto = new FeeResponseDto();
        dto.setId(fee.getId());
        dto.setStudentId(fee.getStudent().getId());
        dto.setAmount(fee.getAmount());
        dto.setDueDate(fee.getDueDate());
        dto.setPaidStatus(fee.getPaidStatus().name());
        dto.setPaymentDate(fee.getPaymentDate());
        return dto;
    }
}