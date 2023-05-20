package com.example.youtube.dto.videoLike;

import com.example.youtube.dto.video.VideoLikeRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoLikeInfo {
    private  Integer id;
    private VideoLikeRequestDTO dto;

    /// preview_attach todo
}
// id,video(id,name,channel(id,name),duration),preview_attach(id,url)