package com.example.youtube.service;

import com.example.youtube.dto.attach.AttachResponseDTO;
import com.example.youtube.dto.channel.ChannelResponseDTO;
import com.example.youtube.dto.playlist.*;
import com.example.youtube.dto.profile.ProfileResponseDTO2;
import com.example.youtube.entity.ChannelEntity;
import com.example.youtube.entity.PlayListEntity;
import com.example.youtube.enums.ProfileRole;
import com.example.youtube.enums.VisibleStatus;
import com.example.youtube.exps.AppBadRequestException;
import com.example.youtube.exps.MethodNotAllowedException;
import com.example.youtube.repository.PlayListVideoRepository;
import com.example.youtube.repository.PlaylistRepository;
import com.example.youtube.util.SpringSecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final ChannelService channelService;
    private final PlayListVideoRepository playListVideoRepository;
    private final PlayListVideoService playListVideoService;


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

    public Boolean delete(Integer id, Integer prtId, ProfileRole role) {
        if (role.equals(ProfileRole.ROLE_ADMIN) || Objects.equals(get(id).getChannel().getProfileId(), prtId)) {
            playlistRepository.deletePlaylist(id);
            return true;
        }
        return false;
    }

    public Page<PlayListInfoDTO> pagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<PlayListEntity> entityPage = playlistRepository.findAll(pageable);
        List<PlayListEntity> entities = entityPage.getContent();
        List<PlayListInfoDTO> dtos = new LinkedList<>();
        entities.forEach(entity -> {
            dtos.add(toDTO(entity));
        });
        return new PageImpl<>(dtos, pageable, entityPage.getTotalElements());
    }

    public List<PlayListInfoDTO> getUsersPlayList(Integer profileId) {
        List<PlayListInfoDTO> dtos = new ArrayList<>();
        List<ChannelEntity> channels = channelService.getChanelByProfileId(profileId);
        channels.forEach(channelEntity -> {
            List<PlayListEntity> entities = playlistRepository.getByChannelId(channelEntity.getId());
            entities.forEach(entity -> {
                dtos.add(toDTO(entity));
            });
        });
        return dtos;
    }
    public List<PlayListShortInfoDTO> getUsersPlayList() {
        List<PlayListShortInfoDTO> dtos = new ArrayList<>();
        Integer profileId = SpringSecurityUtil.getProfileId();
        List<ChannelEntity> channels = channelService.getChanelByProfileId(profileId);
        channels.forEach(channelEntity -> {
            List<PlayListEntity> entities = playlistRepository.getByChannelId(channelEntity.getId());
            entities.forEach(entity -> {
                dtos.add(toShortInfoDTO(entity));
            });
        });
        return dtos;
    }
    public List<PlayListShortInfoDTO> getPlayListByChannelId(String channelId) {
        List<PlayListShortInfoDTO> dtos = new LinkedList<>();
        channelService.get(channelId);
        List<PlayListEntity> entities = playlistRepository.getByChannelId(channelId);
        entities.forEach(entity -> {
            dtos.add(toShortInfoDTO(entity));
        });
        return dtos;
    }
    public PlayListSingleResponseDTO getPlayListById(Integer id) {
        PlayListEntity entity = get(id);
        PlayListSingleResponseDTO dto = new PlayListSingleResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLastUpdateDate(playListVideoRepository.getLastUpdateDateByPlayListId(id).toLocalDate());
        dto.setTotalViewCount(playListVideoService.getTotalViewCountByPlayListId(id));
        return dto;
    }
    private void checkRequiredOwner(String channelId, Integer prtId) {
        if (!channelService.get(channelId).getProfileId().equals(prtId)) {
            throw new MethodNotAllowedException("Not allowed for you");
        }
    }

    public PlayListEntity get(Integer id) {
        Optional<PlayListEntity> entity = playlistRepository.getById(id);
        if (entity.isEmpty()) {
            throw new AppBadRequestException("Item not found");
        }
        return entity.get();
    }

    private PlayListInfoDTO toDTO(PlayListEntity entity) {
        PlayListInfoDTO dto = new PlayListInfoDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setOrderNum(entity.getOrderNum());
        ChannelResponseDTO channel = new ChannelResponseDTO(entity.getChannelId(), entity.getChannel().getName(),
                new AttachResponseDTO(entity.getChannel().getPhoto().getId(), entity.getChannel().getPhoto().getPath()));
        dto.setChannel(channel);
        AttachResponseDTO attach = new AttachResponseDTO(
                entity.getChannel().getProfile().getAttach().getId(),
                entity.getChannel().getProfile().getAttach().getPath());
        ProfileResponseDTO2 profile = new ProfileResponseDTO2(
                entity.getChannel().getProfile().getId(),
                entity.getChannel().getProfile().getName(),
                entity.getChannel().getProfile().getSurname(),
                attach);
        dto.setProfile(profile);
        return dto;
    }

    private PlayListShortInfoDTO toShortInfoDTO(PlayListEntity entity){
        PlayListShortInfoDTO dto = new PlayListShortInfoDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setChannel(new ChannelResponseDTO(entity.getChannel().getId(), entity.getChannel().getName()));
        int countVideo = playListVideoRepository.getCountVideo(entity.getId());
        dto.setVideoCount(countVideo);
//        dto.setVideoList();
        return dto;
    }

    public PlayListEntity toEntity(PlayListRequestDTO dto, PlayListEntity entity) {
        entity.setChannelId(dto.getChannelId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(VisibleStatus.PUBLIC);
        entity.setOrderNum(dto.getOrderNum());
        entity.setVisible(true);
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }

}
