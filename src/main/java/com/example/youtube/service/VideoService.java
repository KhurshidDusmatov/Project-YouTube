package com.example.youtube.service;

import com.example.youtube.entity.VideoEntity;
import com.example.youtube.exps.VideoNotFoundException;
import com.example.youtube.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;

    public VideoEntity get(String id) {
        Optional<VideoEntity> optional = videoRepository.findById(id);
        if (optional.isEmpty()) {
            throw new VideoNotFoundException("video not found");
        }
        return optional.get();
    }
}
