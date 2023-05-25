package com.example.youtube.dto.subscription;

import com.example.youtube.enums.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubscriptionRequestDTO {
    @NotBlank
    private String channelId;
    @NotNull
    private NotificationType notificationType;
}
