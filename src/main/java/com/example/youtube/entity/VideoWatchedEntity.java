//package com.example.youtube.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "video_watched")
//@Getter
//@Setter
//public class VideoWatchedEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private String id;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
//    private ProfileEntity profile;
//    @Column(name = "profile_id")
//    private Integer profileId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "video_id", insertable = false, updatable = false)
//    private VideoEntity video;
//    @Column(name = "video_id")
//    private String videoId;
//    @Column(name = "created_date")
//    private LocalDateTime createdDate = LocalDateTime.now();
//}
