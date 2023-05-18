package com.example.youtube.dto.channel;

import com.example.youtube.dto.attach.AttachResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelResponseDTO {
    private String id;
    private String name;
    private AttachResponseDTO photo;
}
