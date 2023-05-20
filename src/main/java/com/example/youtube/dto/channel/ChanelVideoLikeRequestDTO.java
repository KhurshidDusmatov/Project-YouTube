package com.example.youtube.dto.channel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChanelVideoLikeRequestDTO {
    private String id;
    private String name;
}
