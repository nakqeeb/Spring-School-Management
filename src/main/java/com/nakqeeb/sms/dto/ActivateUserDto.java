package com.nakqeeb.sms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActivateUserDto {

    @NotNull(message = "This field is required")
    private boolean activated;

}
