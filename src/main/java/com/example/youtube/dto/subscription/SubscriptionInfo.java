package com.example.youtube.dto.subscription;

import com.example.youtube.dto.channel.ChannelResponseDTO;
import com.example.youtube.enums.NotificationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionInfo {
    private Integer id;
    private ChannelResponseDTO channel;
    private NotificationType notificationType;
    private LocalDateTime createdDate;

    //  id,channel(id,name,photo(id,url)),notification_type

}
