package com.example.youtube.dto.playlist;

import com.example.youtube.dto.channel.ChannelResponseDTO;
import com.example.youtube.dto.video.VideoShortResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayListShortInfoDTO {
    private Integer id;
    private String name;
    private LocalDateTime createdDate;
    private ChannelResponseDTO channel;
    private Integer videoCount;
    private List<VideoShortResponseDTO> videoList;



}
