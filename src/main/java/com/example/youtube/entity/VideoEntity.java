package com.example.youtube.entity;


import com.example.youtube.enums.VideoStatus;
import com.example.youtube.enums.VideoType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "video")
@Getter
@Setter
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "preview_attach_id")
    private String previewAttachId;
    @OneToOne
    @JoinColumn(name = "preview_attach_id")
    private AttachEntity previewAttach;
    @Column(name = "title")
    private String title;
    @Column(name = "category_id")
    private Integer categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @Column(name = "attach_id")
    private String attachId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id")
    private AttachEntity attach;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name = "published_date")
    private LocalDateTime publishedDate;
    @Enumerated(EnumType.STRING)
    private VideoStatus status;
    @Enumerated(EnumType.STRING)
    private VideoType type;
    @Column(name = "view_count")
    private Integer viewCount;
    @Column(name = "shared_count")
    private Integer sharedCount;
    @Column(name = "description")
    private String description;
    @Column(name = "channel_id")
    private Integer channelId;
    // TODO: 5/17/2023 channel
    @Column(name = "like_count")
    private Integer likeCount;
    @Column(name = "dislike_count")
    private Integer dislikeCount;

}