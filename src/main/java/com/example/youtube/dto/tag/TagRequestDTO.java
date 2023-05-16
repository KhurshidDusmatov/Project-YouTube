package com.example.youtube.dto.tag;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagRequestDTO {
    @NotBlank(message = "email required")
    private String name;
}
