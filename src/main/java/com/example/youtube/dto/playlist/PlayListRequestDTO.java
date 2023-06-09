package com.example.youtube.dto.playlist;

import com.example.youtube.enums.VisibleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class    PlayListRequestDTO {
    @NotNull
    private String channelId;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private VisibleStatus status;
    @NotNull
    private Integer orderNum;



}
