package com.example.youtube.dto.videoTag;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoTagDTO {
    private Integer id;
    @NotBlank
    private String videoId;
    @NotBlank
    private Integer tagId;

}
