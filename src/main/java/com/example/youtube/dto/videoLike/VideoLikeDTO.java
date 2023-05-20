package com.example.youtube.dto.videoLike;

import com.example.youtube.entity.ProfileEntity;
import com.example.youtube.entity.VideoEntity;
import com.example.youtube.enums.EmotionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VideoLikeDTO {
    private Integer id;
    private Integer profileId;
    private String videoId;
    private LocalDateTime createdDate;
    private EmotionType type;
}
