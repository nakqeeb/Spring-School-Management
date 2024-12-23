package com.nakqeeb.sms.controller;

import com.nakqeeb.sms.dto.FeeRequestDto;
import com.nakqeeb.sms.dto.FeeResponseDto;
import com.nakqeeb.sms.entity.PaidStatusEnum;
import com.nakqeeb.sms.service.FeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
@RequiredArgsConstructor
@Tag(name = "Fee", description = "Endpoints for managing fees")
public class FeeController {

    private final FeeService feeService;

    @Operation(
            summary = "Create Fee",
            description = "Creates a new fee. This operation is restricted to Admin only."
    )
    @PostMapping
    public ResponseEntity<FeeResponseDto> createFee(@RequestBody FeeRequestDto feeRequestDto) {
        FeeResponseDto response = feeService.createFee(feeRequestDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Update Fee",
            description = "Updates an existing fee. This operation is restricted to Admin only"
    )
    @PutMapping("/{feeId}")
    public ResponseEntity<FeeResponseDto> updateFee(@PathVariable Long feeId, @RequestBody FeeRequestDto feeRequestDto) {
        FeeResponseDto response = feeService.updateFee(feeId, feeRequestDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get Fees by Student",
            description = "Retrieves a list of fees for a specific student. This operation is restricted to Admin and Principal."
    )
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<FeeResponseDto>> getFeesByStudent(@PathVariable Long studentId) {
        List<FeeResponseDto> response = feeService.getFeesByStudent(studentId);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get Fees by Status",
            description = "Retrieves a list of fees with a specific status (e.g., paid, unpaid). This operation is restricted to Admin and Principal."
    )
    @GetMapping("/status/{status}")
    public ResponseEntity<List<FeeResponseDto>> getFeesByStatus(@PathVariable String status) {
        // System.out.println("Incoming status: " + status);
        // PaidStatusEnum paidStatus = PaidStatusEnum.fromString(status);
        List<FeeResponseDto> response = feeService.getFeesByStatus(status);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Delete Fee",
            description = "Deletes a fee. This operation is restricted to Admin only."
    )
    @DeleteMapping("/{feeId}")
    public ResponseEntity<Void> deleteFee(@PathVariable Long feeId) {
        feeService.deleteFee(feeId);
        return ResponseEntity.noContent().build();
    }
}