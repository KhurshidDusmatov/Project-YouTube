package com.example.youtube.controller;

import com.example.youtube.dto.profile.ProfileChangePasswordDTO;
import com.example.youtube.dto.profile.ProfileCreateRequestDTO;
import com.example.youtube.dto.profile.ProfileDTO;
import com.example.youtube.service.ProfileService;
import com.example.youtube.util.SpringSecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PutMapping("/public/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ProfileChangePasswordDTO dto){
        return ResponseEntity.ok(profileService.changePassword(dto));
    }

    @PutMapping("/change-detail")
    public ResponseEntity<?> changeDetail(@Param("name") String name, @Param("surname") String surname){
        Boolean result = profileService.changeDetail(name, surname, SpringSecurityUtil.getProfileId());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-all-detail")
    public ResponseEntity<?> getAllDetail(){
        ProfileDTO dto = profileService.getAllDetail(SpringSecurityUtil.getProfileId());
        return ResponseEntity.ok(dto);
    }

    @PostMapping("private/create")
    public ResponseEntity<?> create(@RequestBody ProfileCreateRequestDTO dto){
       return ResponseEntity.ok(profileService.create(dto));
    }


}
