package com.example.youtube.controller;

import com.example.youtube.dto.category.CategoryDTO;
import com.example.youtube.dto.channel.ChannelDTO;
import com.example.youtube.dto.tag.TagRequestDTO;
import com.example.youtube.dto.tag.TagResponseDTO;
import com.example.youtube.enums.ProfileRole;
import com.example.youtube.service.ChannelService;
import com.example.youtube.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PostMapping("/public/create")
    public ResponseEntity<ChannelDTO> create(@RequestBody ChannelDTO dto, HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request);
        Integer prtId =(Integer) request.getAttribute("id");
        return ResponseEntity.ok(channelService.create(dto,prtId));
    }
    @PutMapping("/public/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody ChannelDTO dto,
                                    HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request);
        Integer prtId =(Integer) request.getAttribute("id");
        return ResponseEntity.ok(channelService.update(id,dto,prtId));
    }
    @PutMapping("/public/update_photo/{id}")
    public ResponseEntity<?> updatePhoto(@PathVariable("id") String id,
                                    @RequestBody ChannelDTO dto,
                                    HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request);
        Integer prtId =(Integer) request.getAttribute("id");
        return ResponseEntity.ok(channelService.updatePhoto(id,dto,prtId));
    }
    @PutMapping("/public/update_banner/{id}")
    public ResponseEntity<?> updateBanner(@PathVariable("id") String id,
                                         @RequestBody ChannelDTO dto,
                                         HttpServletRequest request) {
        Integer prtId =(Integer) request.getAttribute("id");
        return ResponseEntity.ok(channelService.updateBanner(id,dto,prtId));
    }
}
