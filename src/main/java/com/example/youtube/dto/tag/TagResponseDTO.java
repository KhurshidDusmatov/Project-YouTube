package com.example.youtube.dto.tag;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagResponseDTO {
    private Integer id;
    private String name;
    private LocalDateTime createdDate;
}
