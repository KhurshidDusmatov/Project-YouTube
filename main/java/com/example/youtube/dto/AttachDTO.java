package com.example.youtube.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AttachDTO {
    private String id;
    @NotNull(message = "originalName required")
    @NotBlank(message = "Field must have some value")
    private String originalName;
    @NotNull(message = "path required")
    @NotBlank(message = "Field must have some value")
    private String path;
    @NotNull(message = "size required")
    @NotBlank(message = "Field must have some value")
    private Long size;
    @NotNull(message = "extension required")
    @NotBlank(message = "Field must have some value")
    private String extension;
    @NotNull(message = "createdData required")
    @NotBlank(message = "Field must have some value")
    private LocalDateTime createdData;
    private String url;
}
