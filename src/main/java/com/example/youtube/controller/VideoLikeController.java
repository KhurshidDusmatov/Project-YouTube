package com.example.youtube.controller;

import com.example.youtube.dto.videoLike.VideoLikeInfo;
import com.example.youtube.service.VideoLikeService;
import com.example.youtube.util.SpringSecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/video-like")
@AllArgsConstructor
public class VideoLikeController {
    private final VideoLikeService videoLikeService;
    @GetMapping("/public/like/{id}")
    public ResponseEntity<Boolean> like(@PathVariable("id") String videoId) {
        return ResponseEntity.ok(videoLikeService.like(videoId, SpringSecurityUtil.getProfileId()));
    }
    @GetMapping("/public/dislike/{id}")
    public ResponseEntity<Boolean> dislike(@PathVariable("id") String videoId){
        return ResponseEntity.ok(videoLikeService.dislike(videoId, SpringSecurityUtil.getProfileId()));
    }
    @DeleteMapping("/public/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String videoId){
        return ResponseEntity.ok(videoLikeService.delete(videoId,SpringSecurityUtil.getProfileId()));
    }
    @GetMapping("public/liked-video")
    public ResponseEntity<List<VideoLikeInfo>>likeVideos(){
        return ResponseEntity.ok(videoLikeService.likeVideos(SpringSecurityUtil.getProfileId()));
    }

//    @GetMapping("/private/liked-video/{profileId}")

}
    //4. Get User LikedVideo List By UserId (ADMIN)
