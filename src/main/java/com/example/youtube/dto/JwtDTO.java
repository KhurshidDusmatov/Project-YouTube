package com.example.youtube.dto;

import com.example.youtube.enums.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtDTO {
    private String mail;
    private ProfileRole role;
}
