package com.nakqeeb.sms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class UpdateStudentDto {

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

//    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
//    private String password;

    private Long parentId;
    private Long classId;

}