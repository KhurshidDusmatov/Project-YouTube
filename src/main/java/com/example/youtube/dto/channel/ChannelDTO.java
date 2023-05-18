package com.example.youtube.dto.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDTO {
    private String id;
    @NotNull(message = "originalName required")
    @NotBlank(message = "Field must have some value")
    private String name;
    @NotNull(message = "originalName required")
    @NotBlank(message = "Field must have some value")
    private String photoId;
    @NotNull(message = "originalName required")
    @NotBlank(message = "Field must have some value")
    private String description;
    @NotNull(message = "originalName required")
    @NotBlank(message = "Field must have some value")
    private String bannerId;


}
