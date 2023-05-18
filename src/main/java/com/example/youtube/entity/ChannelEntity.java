package com.example.youtube.entity;

import com.example.youtube.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "channel")
@Getter
@Setter
public class ChannelEntity {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id",insertable = false,updatable = false)
    private AttachEntity photo;

    @Column(name = "photo_id")
    private String photoId;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private GeneralStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banner_id",insertable = false,updatable = false)
    private AttachEntity banner;

    @Column(name = "banner_id")
    private String bannerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",insertable = false,updatable = false)
    private ProfileEntity profile;

    @Column(name = "profile_id")
    private Integer profileId;
}
