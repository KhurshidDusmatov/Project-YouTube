package com.example.youtube.controller;

import com.example.youtube.dto.tag.TagRequestDTO;
import com.example.youtube.dto.tag.TagResponseDTO;
import com.example.youtube.enums.ProfileRole;
import com.example.youtube.service.TagService;
import com.example.youtube.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag")
@AllArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping("/public")
    public ResponseEntity<TagResponseDTO> create(@RequestBody @Valid TagRequestDTO dto) {
        return ResponseEntity.ok(tagService.create(dto));
    }

    @PutMapping("/private/{id}")
    public ResponseEntity<TagResponseDTO> update(@RequestBody @Valid TagRequestDTO dto,
                                                 @PathVariable Integer id) {
        return ResponseEntity.ok(tagService.update(id, dto));
    }

    @DeleteMapping("/private/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(tagService.delete(id));
    }

    @GetMapping("/public")
    public ResponseEntity<List<TagResponseDTO>> getList() {
        List<TagResponseDTO> list = tagService.getList();
        return ResponseEntity.ok(list);
    }
}
