package com.example.youtube.controller;

import com.example.youtube.dto.VideoTagDTO;
import com.example.youtube.dto.playlist.PlayListInfoDTO;
import com.example.youtube.service.VideoTagService;
import com.example.youtube.util.SpringSecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ap1/v1/video-tag")
public class VideoTagController {

    @Autowired
    private VideoTagService videoTagService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid VideoTagDTO dto) {
        Integer prtId = SpringSecurityUtil.getProfileId();
//        videoTagService.create(dto, prtId);
        return ResponseEntity.ok(null);
    }
}
