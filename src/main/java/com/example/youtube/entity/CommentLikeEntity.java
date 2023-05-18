package com.example.youtube.entity;

import com.example.youtube.enums.CommentLikeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment_like")
public class CommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "profile_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileEntity profile;
    @JoinColumn(name = "comment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CommentEntity comment;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private CommentLikeType type;
}
//id,profile_id,comment_id,created_date,type(Like,Dislike)