package com.example.youtube.service;

import com.example.youtube.dto.playlist.PlayListInfoDTO;
import com.example.youtube.dto.playlist.PlayListRequestDTO;
import com.example.youtube.dto.playlist.PlayListUpdateStatusDTO;
import com.example.youtube.entity.PlayListEntity;
import com.example.youtube.enums.VisibleStatus;
import com.example.youtube.exps.AppBadRequestException;
import com.example.youtube.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    public PlayListInfoDTO create(PlayListRequestDTO dto, Integer prtId) {
        checkRequiredOwner(dto.getChannelId(), prtId);
        PlayListEntity entity = new PlayListEntity();
        PlayListEntity result = toEntity(dto, entity);
        playlistRepository.save(result);
        return toDTO(result);
    }

    public PlayListInfoDTO update(PlayListRequestDTO dto, Integer id, Integer prtId) {
        checkRequiredOwner(dto.getChannelId(), prtId);
        PlayListEntity entity = get(id);
        PlayListEntity result = toEntity(dto, entity);
        playlistRepository.save(result);
        return toDTO(result);
    }

    public Boolean updateStatus(PlayListUpdateStatusDTO dto, Integer prtId) {
        checkRequiredOwner(get(dto.getId()).getChannelId(), prtId);
        playlistRepository.updateStatus(dto.getStatus(), dto.getId());
        return true;
    }



    private void checkRequiredOwner(String channelId, Integer prtId) {
//        if (){
//            throw new MethodNotAllowedException("You have not permission");
//        }
    }
    public PlayListEntity get(Integer id){
        Optional<PlayListEntity> entity = playlistRepository.findById(id);
        if (entity.isEmpty()){
            throw new AppBadRequestException("Item not found");
        }
        return entity.get();
    }
    private PlayListInfoDTO toDTO(PlayListEntity entity){
        PlayListInfoDTO dto = new PlayListInfoDTO();
        dto.setId(entity.getId());
        dto.setChannelId(entity.getChannelId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setOrderNum(entity.getOrderNum());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public PlayListEntity toEntity(PlayListRequestDTO dto, PlayListEntity entity){
        entity.setChannelId(dto.getChannelId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(VisibleStatus.PUBLIC);
        entity.setOrderNum(dto.getOrderNum());
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }

}
