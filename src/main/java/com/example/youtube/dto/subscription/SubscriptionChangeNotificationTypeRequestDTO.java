package com.example.youtube.dto.subscription;

import com.example.youtube.enums.NotificationType;
import com.example.youtube.enums.SubscriptionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionChangeNotificationTypeRequestDTO {
    @NotBlank
    private String channelId;
    @NotNull
    private NotificationType notificationType;
}
