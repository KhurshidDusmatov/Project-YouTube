package com.example.youtube.service;

import com.example.youtube.dto.videoLike.VideoLikeInfo;
import com.example.youtube.entity.VideoLikeEntity;
import com.example.youtube.enums.EmotionType;
import com.example.youtube.repository.VideoLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VideoLikeService {
    private final VideoLikeRepository videoLikeRepository;
    private final VideoService videoService;

    public boolean like(String videoId, Integer profileId) {
        makeEmotion(videoId, profileId, EmotionType.LIKE);
        return Boolean.TRUE;
    }

    public boolean dislike(String videoId, Integer profileId) {
        makeEmotion(videoId, profileId, EmotionType.DISLIKE);
        return Boolean.TRUE;
    }

    public boolean delete(String videoId, Integer profileId) {
        videoLikeRepository.delete(videoId, profileId);
        return Boolean.TRUE;
    }

    private void makeEmotion(String videoId, Integer profileId, EmotionType type) {
        Optional<VideoLikeEntity> optional = videoLikeRepository
                .findByVideoIdAndProfileId(videoId, profileId);
        if (optional.isEmpty()) {
            VideoLikeEntity entity = new VideoLikeEntity();
            entity.setVideoId(videoId);
            entity.setProfileId(profileId);
            entity.setType(type);
            videoLikeRepository.save(entity);
        } else {
            videoLikeRepository.update(type, videoId, profileId);
        }
    }

    public List<VideoLikeInfo> likeVideos(Integer profileId) {
        List<VideoLikeInfo> responseList = new LinkedList<>();
        videoLikeRepository.getEmotionVideos(profileId,EmotionType.LIKE.name()).forEach(entity -> {
            VideoLikeInfo info = new VideoLikeInfo();
            info.setId(profileId);
            info.setDto(videoService.getVideoLikeRequestDto(entity.getVideo()));
            responseList.add(info);
        });
        return responseList;
    }
}
