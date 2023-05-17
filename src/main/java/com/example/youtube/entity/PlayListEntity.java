package com.example.youtube.entity;

import com.example.youtube.enums.VisibleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "playlist")
public class PlayListEntity {
//    id,channel_id,name,description,status(private,public),order_num
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "channel_id")
    private String channelId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    private ChannelEntity channel;
    @Column(name = "name")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private VisibleStatus status;
    @Column(name = "order_num")
    private Integer orderNum;
    @Column(name = "created_date")
    private LocalDateTime createdDate;



}
