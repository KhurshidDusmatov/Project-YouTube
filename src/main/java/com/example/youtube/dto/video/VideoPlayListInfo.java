package com.example.youtube.dto.video;

import com.example.youtube.dto.attach.AttachResponseDTO;
import com.example.youtube.dto.channel.ChannelResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class VideoPlayListInfo {
    private String id;
    private String title;
    private AttachResponseDTO previewAttach;
    private LocalDateTime publishedDate;
    private Integer viewCount;
}
