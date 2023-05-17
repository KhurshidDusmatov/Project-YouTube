package com.example.youtube.dto.profile;

import com.example.youtube.enums.ProfileRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProfileCreateRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private ProfileRole role;

}
