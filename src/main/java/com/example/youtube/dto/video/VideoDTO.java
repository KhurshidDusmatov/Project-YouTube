package com.example.youtube.dto.video;

import com.example.youtube.dto.playlist.PlayResponseDTO;
import com.example.youtube.dto.profile.ProfileResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoDTO {
    private VideoShortInfo videoShortInfo;
    private ProfileResponseDTO owner;
    private PlayResponseDTO playList;
}
