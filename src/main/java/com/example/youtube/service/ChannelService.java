package com.example.youtube.service;

import com.example.youtube.entity.ChannelEntity;
import com.example.youtube.entity.VideoEntity;
import com.example.youtube.exps.VideoNotFoundException;
import com.example.youtube.repository.ChannelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepository;
    public ChannelEntity get(String id) {
        Optional<ChannelEntity> optional = channelRepository.findById(id);
        if (optional.isEmpty()) {
            throw new VideoNotFoundException("Channel not found");
        }
        return optional.get();
    }
}
