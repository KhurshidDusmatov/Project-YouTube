package com.example.youtube.dto.comment;

import com.example.youtube.entity.ProfileEntity;
import com.example.youtube.entity.VideoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDTO {

    private Integer id;
    private Integer profileId;
    private String videoId;
    private String content;
    private Integer replyPid;
    private Integer like_count;
    private Integer dislike_count;
    private LocalDateTime createdDate;
}
