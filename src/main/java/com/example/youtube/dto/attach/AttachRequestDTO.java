package com.example.youtube.dto.attach;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachRequestDTO {
    private String id;
    @NotNull(message = "originalName required")
    @NotBlank(message = "Field must have some value")
    private String originalName;
    @NotNull(message = "url required")
    @NotBlank(message = "Field must have some value")
    private String url;
}
