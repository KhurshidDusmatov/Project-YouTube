package com.example.youtube.dto.subscription;

import com.example.youtube.enums.SubscriptionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionChangeStatusRequestDTO {
    @NotBlank
    private String channelId;
    @NotNull
    private SubscriptionStatus status;
}
