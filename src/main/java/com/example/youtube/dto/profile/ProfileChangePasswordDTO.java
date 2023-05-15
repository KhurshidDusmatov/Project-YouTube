package com.example.youtube.dto.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProfileChangeStatusDTO {
    @NotBlank(message = "email required")
    @NotNull(message = "email required")
    private String email;
    @NotBlank(message = "password required")
    @NotNull(message = "password required")
    private String oldPassword;
    @NotBlank(message = "password required")
    @NotNull(message = "password required")
    private String newPassword;
}
