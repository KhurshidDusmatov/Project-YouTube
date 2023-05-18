package com.example.youtube.dto.video;

import com.example.youtube.enums.VideoStatus;
import com.example.youtube.enums.VideoType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoRequestDTO {
    @NotBlank
    private String previewAttachId;
    @NotBlank
    private String title;
    @NotBlank
    private String categoryId;
    @NotBlank
    private String attachId;
    private VideoStatus status;
    private VideoType type;
    @NotBlank
    private String description;
    @NotBlank
    private String channelId;
}
