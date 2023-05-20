package com.example.youtube.dto.playlist;

import com.example.youtube.dto.channel.ChannelResponseDTO;
import com.example.youtube.dto.profile.ProfileResponseDTO;
import com.example.youtube.dto.profile.ProfileResponseDTO2;
import com.example.youtube.enums.VisibleStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlayListInfoDTO {

//    id,name,description,status(private,public),order_num,
//    channel(id,name,photo(id,url), profile(id,name,surname,photo(id,url)))
    private Integer id;
    private String name;
    private String description;
    private VisibleStatus status;
    private Integer orderNum;
    private ChannelResponseDTO channel;
    private ProfileResponseDTO2 profile;



}
