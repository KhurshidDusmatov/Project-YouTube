package com.example.youtube.dto.channel;

import com.example.youtube.dto.attach.AttachResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelResponseDTO {
    private String id;
    private String name;
    private AttachResponseDTO photo;

    public ChannelResponseDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
