package com.nakqeeb.sms.dto;

import com.nakqeeb.sms.entity.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterUserDto {
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email should not exceed 100 characters")
    private String email;
    private String password;
    private String name;
//    private RoleEnum role;
    private String contactNumber;
    private String address;
    private Date dateOfBirth;
    private String profilePicture;
}