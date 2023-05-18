package com.example.youtube.dto.video;

import com.example.youtube.enums.VideoStatus;
import com.example.youtube.enums.VideoType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoResponseDTO {
    private String id;
    private String previewAttachId;
    private String title;
    private String categoryId;
    private String attachId;
    private LocalDateTime createdDate;
    private VideoStatus status;
    private VideoType type;
    private String description;
    private String channelId;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer viewCount;
}
