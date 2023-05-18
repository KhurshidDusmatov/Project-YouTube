package com.example.youtube.dto.playlist;

import com.example.youtube.enums.VisibleStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayListUpdateStatusDTO {

    @NotNull
    private Integer id;
    @NotBlank
    private VisibleStatus status;
}
