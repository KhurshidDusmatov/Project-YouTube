package com.example.youtube.dto.video;

import com.example.youtube.dto.attach.AttachResponseDTO;
import com.example.youtube.dto.channel.ChannelResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class VideoShortInfo {
    private String id;
    private AttachResponseDTO previewAttach;
    private LocalDateTime publishedDate;
    private ChannelResponseDTO channel;
    private Integer viewCount;
    // duration;
}
