package com.example.youtube.dto.comment;

import com.example.youtube.entity.ProfileEntity;
import com.example.youtube.entity.VideoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {

    private VideoEntity video;
    private String content;
    @JoinColumn(name = "reply_id")
    @ManyToOne
    private ProfileEntity replyPid;
    @Column(name = "like_count")
    private Integer like_count;
    @Column(name = "dislike_count")
    private Integer dislike_count;
}
