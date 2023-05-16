package com.example.youtube.dto.jwt;


import com.example.youtube.enums.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtDTO {
    private String email;
    private ProfileRole role;
}
