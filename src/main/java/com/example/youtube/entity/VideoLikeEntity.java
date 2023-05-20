package com.example.youtube.entity;

import com.example.youtube.enums.EmotionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "video_like")
@Entity
public class VideoLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "profile_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileEntity profile;
    @Column(name = "profile_id")
    private Integer profileId;
    @JoinColumn(name = "video_id",insertable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private VideoEntity video;
    @Column(name = "vide0_id")
    private String videoId;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private EmotionType type;

}
