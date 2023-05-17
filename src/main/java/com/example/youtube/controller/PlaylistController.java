package com.example.youtube.controller;

import com.example.youtube.dto.playlist.PlayListInfoDTO;
import com.example.youtube.dto.playlist.PlayListRequestDTO;
import com.example.youtube.dto.playlist.PlayListUpdateStatusDTO;
import com.example.youtube.service.PlaylistService;
import com.example.youtube.util.SpringSecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ap1/v1/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid PlayListRequestDTO dto) {
        Integer prtId = SpringSecurityUtil.getProfileId();
        PlayListInfoDTO result = playlistService.create(dto, prtId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid PlayListRequestDTO dto,
                                    @RequestParam("id") Integer id) {
        Integer prtId = SpringSecurityUtil.getProfileId();
        PlayListInfoDTO result = playlistService.update(dto,id, prtId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update-status")
    public ResponseEntity<?> updateStatus(@RequestBody @Valid PlayListUpdateStatusDTO dto,
                                          @RequestParam("id") Integer id) {
        Integer prtId = SpringSecurityUtil.getProfileId();
        return ResponseEntity.ok(playlistService.updateStatus(dto, prtId));
    }
}
