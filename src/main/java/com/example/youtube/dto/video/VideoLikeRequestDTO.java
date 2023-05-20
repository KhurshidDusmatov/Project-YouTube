package com.example.youtube.dto.video;

import com.example.youtube.dto.channel.ChanelVideoLikeRequestDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
// this class video like uchun maxsus yaratildi 😁
public class VideoLikeRequestDTO {
    private String id;  // this id video
    private String name;
    private ChanelVideoLikeRequestDTO dto;
}
