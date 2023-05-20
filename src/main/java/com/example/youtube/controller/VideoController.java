package com.example.youtube.controller;

import com.example.youtube.dto.video.*;
import com.example.youtube.service.VideoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/video")
@AllArgsConstructor
public class VideoController {
    private final VideoService videoService;

    // 1
    @PostMapping
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<VideoResponseDTO> create(@RequestBody @Valid VideoRequestDTO dto) {
        VideoResponseDTO response = videoService.create(dto);
        return ResponseEntity.ok(response);
    }

    // 3
    @PutMapping("/change-status/{id}")
    public ResponseEntity<VideoResponseDTO> changeStatus(@PathVariable String id) {
        VideoResponseDTO response = videoService.changeStatus(id);
        return ResponseEntity.ok(response);
    }

    // 4
    @PutMapping("/{id}")
    public ResponseEntity<?> videoViewCountById(@PathVariable String id) {
        Integer response = videoService.videoViewCountById(id);
        return ResponseEntity.ok(response);
    }

    // 5
    @GetMapping("/video-paging-by-category")
    public ResponseEntity<Page<VideoShortInfo>> videoPagingByCategoryId(@RequestParam("id") String id,
                                                                        @RequestParam("size") int size,
                                                                        @RequestParam("page") int page) {
        Page<VideoShortInfo> response = videoService.videoPagingByCategoryId(id, size, page);
        return ResponseEntity.ok(response);
    }

    // 6
    @GetMapping("/search-by-title/{title}")
    public ResponseEntity<List<VideoShortInfo>> getVideoList(@PathVariable String title) {
        List<VideoShortInfo> list = videoService.getVideoList(title);
        return ResponseEntity.ok(list);
    }

    // 8
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<VideoFullInfo> getById(@PathVariable String id) {
        VideoFullInfo response = videoService.getById(id);
        return ResponseEntity.ok(response);
    }

    // 9
    @GetMapping("/private/paging")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<VideoDTO>> pagingAdmin(@RequestParam("size") int size, @RequestParam("page") int page) {
        Page<VideoDTO> response = videoService.pagingAdmin(size, page);
        return ResponseEntity.ok(response);
    }

    // 10
    @GetMapping("/public/paging")
    public ResponseEntity<Page<VideoPlayListInfo>> paging(@RequestParam(value = "size") int size, @RequestParam("page") int page) {
        Page<VideoPlayListInfo> response = videoService.paging(size, page);
        return ResponseEntity.ok(response);
    }
}
