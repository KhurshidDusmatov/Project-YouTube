package com.example.youtube.controller;

import com.example.youtube.dto.comment.CommentFullResponseDTO;
import com.example.youtube.dto.comment.CommentInfoDTO;
import com.example.youtube.dto.comment.CommentRequestDTO;
import com.example.youtube.dto.comment.CommentResponseDTO;
import com.example.youtube.entity.CommentEntity;
import com.example.youtube.enums.ProfileRole;
import com.example.youtube.service.CommentService;
import com.example.youtube.util.JwtUtil;
import com.example.youtube.util.SpringSecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/public/{attach-id}")
    public ResponseEntity<?> create(@PathVariable("attach-id") String attachId, @RequestBody CommentRequestDTO dto) {
        return ResponseEntity.ok(commentService.create(dto, SpringSecurityUtil.getProfileId(), attachId));
    }
    @PutMapping("/public/{comment-id}")
    public ResponseEntity<?> update(@PathVariable("comment-id") Integer commentId,
                                    @RequestParam("content") String content) {
        return ResponseEntity.ok(commentService.update(commentId, content));
    }
    @DeleteMapping("/private/{comment-id}")
    public ResponseEntity<?> delete(@PathVariable("comment-id") Integer commentId) {
        return ResponseEntity.ok(commentService.delete(commentId));
    }
    @GetMapping("/private/paging")
    public ResponseEntity<Page<CommentResponseDTO>> paging(@RequestParam(value = "page", defaultValue = "1") int page,
                                                           @RequestParam(value = "size", defaultValue = "30") int size) {
        return ResponseEntity.ok(commentService.paging(page, size));

    }
    @GetMapping("/private/pid{pid}")
    public ResponseEntity<?> getByPid(@PathVariable("pid") Integer profileId) {
        return ResponseEntity.ok(commentService.getByPid(profileId));
    }
    @GetMapping("/private")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(commentService.getByPid(SpringSecurityUtil.getProfileId()));
    }
    @GetMapping("/public/vId/{vId}")
    public ResponseEntity<List<CommentInfoDTO>> getByVideoId(@PathVariable("vId") String vId) {
        return ResponseEntity.ok(commentService.getByVideoId(vId));
    }
    @GetMapping("/public/repId/{repId}")
    private ResponseEntity<List<CommentInfoDTO>> getByRepliedId(@PathVariable("repId")Integer repId){
        return ResponseEntity.ok(commentService.getByRepliedId(repId));
    }

}
