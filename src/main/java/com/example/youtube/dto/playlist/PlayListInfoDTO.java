package com.example.youtube.dto.playlist;

import com.example.youtube.enums.VisibleStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlayListInfoDTO {
    private Integer id;
    private String channelId;
    private String name;
    private String description;
    private VisibleStatus status;
    private Integer orderNum;
    private LocalDateTime createdDate;



}
