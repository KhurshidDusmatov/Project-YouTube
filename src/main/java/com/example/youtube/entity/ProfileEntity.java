package com.example.youtube.entity;

import com.example.youtube.enums.GeneralStatus;
import com.example.youtube.enums.ProfileRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
@Getter
@Setter
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    //    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "attach_id")
//    private AttachEntity attach;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ProfileRole role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private GeneralStatus status;
    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();


}
