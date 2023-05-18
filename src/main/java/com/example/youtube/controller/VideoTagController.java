package com.example.youtube.controller;

import com.example.youtube.dto.videoTag.VideoTagDTO;
import com.example.youtube.dto.videoTag.VideoTagResponseDTO;
import com.example.youtube.enums.ProfileRole;
import com.example.youtube.service.VideoTagService;
import com.example.youtube.util.SpringSecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ap1/v1/video-tag")
public class VideoTagController {

    @Autowired
    private VideoTagService videoTagService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid VideoTagDTO dto) {
        Integer prtId = SpringSecurityUtil.getProfileId();
        return ResponseEntity.ok(videoTagService.create(dto, prtId));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody @Valid VideoTagDTO dto) {
        Integer prtId = SpringSecurityUtil.getProfileId();
        return ResponseEntity.ok(videoTagService.delete(dto, prtId));
    }
    @GetMapping("private/get-all")
    public ResponseEntity<List<VideoTagResponseDTO>> delete(@RequestParam("videoId") String videoId) {
        ProfileRole prtRole = SpringSecurityUtil.getProfileRole();
        return ResponseEntity.ok(videoTagService.getVideoTagListByVideoId(videoId, prtRole));
    }


}
