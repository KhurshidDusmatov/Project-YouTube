package com.example.youtube.controller;

import com.example.youtube.dto.profile.ProfileChangePasswordDTO;
import com.example.youtube.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PutMapping("/public/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ProfileChangePasswordDTO dto){
        return ResponseEntity.ok(profileService.changePassword(dto));
    }
}
