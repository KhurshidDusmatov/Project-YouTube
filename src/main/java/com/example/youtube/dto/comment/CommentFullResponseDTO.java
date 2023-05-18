package com.example.youtube.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentFullResponseDTO {
    private Integer id;
    private String content;
    private Integer likeCount;
    private Integer dislikeCount;
    private LocalDateTime createdDate;
}
