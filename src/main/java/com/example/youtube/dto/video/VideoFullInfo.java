package com.example.youtube.dto.video;

import com.example.youtube.dto.attach.AttachResponseDTO;
import com.example.youtube.dto.category.CategoryResponseDTO;
import com.example.youtube.dto.channel.ChannelResponseDTO;
import com.example.youtube.dto.tag.TagResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class VideoFullInfo {
    private String id;
    private String description;
    private AttachResponseDTO previewAttach;
    private AttachResponseDTO attach;
    private CategoryResponseDTO category;
    private List<TagResponseDTO> tagList;
    private LocalDateTime publishedDate;
    private ChannelResponseDTO channel;
    private Integer viewCount;
    private Integer sharedCount;


    //Like(like_count,dislike_count,
    //                isUserLiked,IsUserDisliked),duration
}
