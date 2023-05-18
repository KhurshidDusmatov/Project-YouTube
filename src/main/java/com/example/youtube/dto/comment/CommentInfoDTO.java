package com.example.youtube.dto.comment;

import com.example.youtube.dto.profile.ProfileCommentResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentInfoDTO {
    private Integer id;
    private String content;
    private LocalDateTime createdDate;
    private Integer likeCount;
    private Integer dislikeCount;
    private ProfileCommentResponseDTO profile;
}
