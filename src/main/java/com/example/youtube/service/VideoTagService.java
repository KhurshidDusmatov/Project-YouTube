package com.example.youtube.service;


import com.example.youtube.dto.videoTag.VideoTagDTO;
import com.example.youtube.dto.videoTag.VideoTagResponseDTO;
import com.example.youtube.entity.VideoTagEntity;
import com.example.youtube.enums.ProfileRole;
import com.example.youtube.exps.MethodNotAllowedException;
import com.example.youtube.repository.VideoTagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoTagService {
    private final VideoTagRepository videoTagRepository;
    private final VideoService videoService;
    private final ChannelService channelService;
    private final TagService tagService;

    public VideoTagDTO create(VideoTagDTO dto, Integer prtId){
        checkDetails(dto, prtId);
        VideoTagEntity entity = new VideoTagEntity();
        entity.setVideoId(dto.getVideoId());
        entity.setTagId(dto.getTagId());
        entity.setVisible(true);
        videoTagRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
    public Boolean delete(VideoTagDTO dto, Integer prtId){
        checkDetails(dto, prtId);
        videoTagRepository.changeVisible(dto.getVideoId(), dto.getTagId());
        return true;
    }
    public List<VideoTagResponseDTO> getVideoTagListByVideoId(String videoId, ProfileRole role){
        if (!role.equals(ProfileRole.ROLE_ADMIN)){
            throw new MethodNotAllowedException("Not allowed for you");
        }
        videoService.get(videoId);
        List<VideoTagResponseDTO> dtos = new ArrayList<>();
        List<VideoTagEntity> entities = videoTagRepository.getVideoTagListByVideoId(videoId);
        entities.forEach(entity -> {
            VideoTagResponseDTO dto = new VideoTagResponseDTO();
            dto.setId(entity.getId());
            dto.setVideoId(entity.getVideoId());
            dto.getTag().setId(entity.getTagId());
            dto.getTag().setName(entity.getTag().getName());
            dto.setCreatedDate(entity.getCreatedDate());
            dtos.add(dto);
        });
        return dtos;
    }
    private void checkDetails(VideoTagDTO dto, Integer prtId){
        videoService.get(dto.getVideoId());
        tagService.get(dto.getTagId());
        Integer ownerId = channelService.get(videoService.get(dto.getVideoId()).getChannelId()).getProfileId();
        if (prtId!=ownerId){
            throw new MethodNotAllowedException("Not allowed for you");
        }
    }

}
