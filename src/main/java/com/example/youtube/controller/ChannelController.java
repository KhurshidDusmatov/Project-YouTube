package com.example.youtube.controller;

import com.example.youtube.dto.channel.ChannelDTO;
import com.example.youtube.service.ChannelService;
import com.example.youtube.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PostMapping("/private/create")
    public ResponseEntity<ChannelDTO> create(@RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.create(dto));
    }
    @PutMapping("/public/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.update(id,dto));
    }
    @PutMapping("/private/update_photo/{id}")
    public ResponseEntity<?> updatePhoto(@PathVariable("id") String id,
                                    @RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.updatePhoto(id,dto));
    }
    @PutMapping("/private/update_banner/{id}")
    public ResponseEntity<?> updateBanner(@PathVariable("id") String id,
                                         @RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.updateBanner(id,dto));
    }
}
