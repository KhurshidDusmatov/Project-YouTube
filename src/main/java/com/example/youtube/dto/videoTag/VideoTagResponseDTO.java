package com.example.youtube.dto.videoTag;

import com.example.youtube.dto.tag.TagResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class VideoTagResponseDTO{
    private Integer id;
    private String videoId;
    private TagResponseDTO tag;
    private LocalDateTime createdDate;
}
