package com.example.youtube.service;

import com.example.youtube.dto.attach.AttachResponseDTO;
import com.example.youtube.dto.channel.ChannelResponseDTO;
import com.example.youtube.dto.subscription.*;
import com.example.youtube.entity.SubscriptionEntity;
import com.example.youtube.exps.ItemNotFoundException;
import com.example.youtube.repository.SubscriptionRepository;
import com.example.youtube.util.SpringSecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final ChannelService channelService;

    public SubscriptionResponseDTO create(SubscriptionRequestDTO dto) {
        SubscriptionEntity entity = new SubscriptionEntity();
        entity.setChannelId(dto.getChannelId());
        entity.setNotificationType(dto.getNotificationType());
        entity.setProfileId(SpringSecurityUtil.getProfileId());
        subscriptionRepository.save(entity);
        return toResponseDTO(entity);
    }

    public SubscriptionResponseDTO toResponseDTO(SubscriptionEntity entity) {
        SubscriptionResponseDTO dto = new SubscriptionResponseDTO();
        dto.setId(entity.getId());
        dto.setChannel(entity.getChannel());
        dto.setProfile(entity.getProfile());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public SubscriptionResponseDTO changeStatus(SubscriptionChangeStatusRequestDTO dto) {
        Optional<SubscriptionEntity> optional =
                subscriptionRepository.findByProfileIdAndChannelId(SpringSecurityUtil.getProfileId(), dto.getChannelId());
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("subscription not found");
        }
        SubscriptionEntity entity = optional.get();
        entity.setStatus(dto.getStatus());
        subscriptionRepository.save(entity);
        return toResponseDTO(entity);
    }

    public SubscriptionResponseDTO changeNotificationType(SubscriptionChangeNotificationTypeRequestDTO dto) {
        Optional<SubscriptionEntity> optional =
                subscriptionRepository.findByProfileIdAndChannelId(SpringSecurityUtil.getProfileId(), dto.getChannelId());
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("subscription not found");
        }
        SubscriptionEntity entity = optional.get();
        entity.setNotificationType(dto.getNotificationType());
        subscriptionRepository.save(entity);
        return toResponseDTO(entity);
    }

    public List<SubscriptionInfo> getList() {
        List<SubscriptionEntity> entityList = subscriptionRepository.getList(SpringSecurityUtil.getProfileId());
        List<SubscriptionInfo> response = new LinkedList<>();
        for (SubscriptionEntity entity : entityList) {
            SubscriptionInfo info = new SubscriptionInfo();
            info.setChannel(new ChannelResponseDTO(entity.getChannelId(),
                    entity.getChannel().getName(),
                    new AttachResponseDTO(entity.getChannel().getPhotoId(), entity.getChannel().getPhoto().getPath())));
            info.setId(entity.getId());
            info.setNotificationType(entity.getNotificationType());
            response.add(info);
        }
        return response;
    }

    public List<SubscriptionInfo> getListByUserId(Integer userId) {
        List<SubscriptionEntity> entityList = subscriptionRepository.getList(userId);
        List<SubscriptionInfo> response = new LinkedList<>();
        for (SubscriptionEntity entity : entityList) {
            SubscriptionInfo info = new SubscriptionInfo();
            info.setChannel(new ChannelResponseDTO(entity.getChannelId(),
                    entity.getChannel().getName(),
                    new AttachResponseDTO(entity.getChannel().getPhotoId(), entity.getChannel().getPhoto().getPath())));
            info.setId(entity.getId());
            info.setNotificationType(entity.getNotificationType());
            info.setCreatedDate(entity.getCreatedDate());
            response.add(info);
        }
        return response;
    }
}
