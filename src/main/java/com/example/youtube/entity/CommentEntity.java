package com.example.youtube.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    @ManyToOne
    private ProfileEntity profile;
    @Column(name = "profile_id")
    private Integer profileId;
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    @ManyToOne
    private VideoEntity video;
    @Column(name = "video_id")
    private String videoId;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @JoinColumn(name = "reply_id",insertable = false,updatable = false)
    @ManyToOne
    private ProfileEntity reply;
    @Column(name = "reply_id")
    private Integer replyPid;
    @Column(name = "like_count")
    private Integer likeCount;
    @Column(name = "dislike_count")
    private Integer dislikeCount;
    @Column(name = "created_date")
    private LocalDateTime createdDate=LocalDateTime.now();

}
