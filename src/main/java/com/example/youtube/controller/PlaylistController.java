package com.example.youtube.controller;

import com.example.youtube.dto.playlist.PlayListInfoDTO;
import com.example.youtube.dto.playlist.PlayListRequestDTO;
import com.example.youtube.dto.playlist.PlayListUpdateStatusDTO;
import com.example.youtube.enums.ProfileRole;
import com.example.youtube.service.PlaylistService;
import com.example.youtube.util.SpringSecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @DeleteMapping("/delete")
    public ResponseEntity<?> updateStatus(@RequestParam("id") Integer id) {
        ProfileRole role = SpringSecurityUtil.getProfileRole();
        Integer prtId = SpringSecurityUtil.getProfileId();
        return ResponseEntity.ok(playlistService.delete(id, prtId, role));
    }

    @GetMapping("private/pagination")
    public ResponseEntity<?> paging(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "size", defaultValue = "4") int size){
        Page<PlayListInfoDTO> pagination = playlistService.pagination(page, size);
        return ResponseEntity.ok(pagination);
    }
}