package com.example.youtube.dto.playlist;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class PlayListSingleResponseDTO {
    private Integer id;
    private String name;
    private Integer totalViewCount;
    private LocalDate lastUpdateDate;
}
