package com.example.youtube.controller;

import com.example.youtube.dto.subscription.*;
import com.example.youtube.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscription")
@AllArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionResponseDTO> create(@RequestBody @Valid SubscriptionRequestDTO dto) {
        SubscriptionResponseDTO response = subscriptionService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/change-status")
    public ResponseEntity<SubscriptionResponseDTO> changeStatus(@RequestBody @Valid SubscriptionChangeStatusRequestDTO dto) {
        SubscriptionResponseDTO response = subscriptionService.changeStatus(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/change-notification-type")
    public ResponseEntity<SubscriptionResponseDTO> changeStatus(@RequestBody @Valid SubscriptionChangeNotificationTypeRequestDTO dto) {
        SubscriptionResponseDTO response = subscriptionService.changeNotificationType(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionInfo>> getList() {
        List<SubscriptionInfo> response = subscriptionService.getList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("ADMIN")
    public ResponseEntity<List<SubscriptionInfo>> getListByUserId(@PathVariable Integer userId) {
        List<SubscriptionInfo> response = subscriptionService.getListByUserId(userId);
        return ResponseEntity.ok(response);
    }
}
