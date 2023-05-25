package com.example.youtube.dto.subscription;

import com.example.youtube.entity.ChannelEntity;
import com.example.youtube.entity.ProfileEntity;
import com.example.youtube.enums.SubscriptionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionResponseDTO {
    private Integer id;
    private ProfileEntity profile;
    private ChannelEntity channel;
    private LocalDateTime createdDate;
    private SubscriptionStatus status;
}
