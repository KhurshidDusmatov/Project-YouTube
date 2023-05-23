package com.example.youtube.service;

import com.example.youtube.config.security.CustomUserDetails;
import com.example.youtube.config.security.CustomUserDetailsService;
import com.example.youtube.dto.attach.AttachDTO;
import com.example.youtube.dto.channel.ChanelVideoLikeRequestDTO;
import com.example.youtube.dto.channel.ChannelDTO;
import com.example.youtube.entity.AttachEntity;
import com.example.youtube.entity.ChannelEntity;
import com.example.youtube.enums.GeneralStatus;
import com.example.youtube.exps.AppBadRequestException;
import com.example.youtube.exps.ItemNotFoundException;
import com.example.youtube.repository.ChannelRepository;
import com.example.youtube.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AttachService attachService;

    public ChannelDTO create(ChannelDTO channelDTO) {
        Integer userId = SpringSecurityUtil.getProfileId();
        ChannelEntity entity = new ChannelEntity();
        entity.setName(channelDTO.getName());
        attachService.get(channelDTO.getBannerId());
        entity.setBannerId(channelDTO.getBannerId());
        attachService.get(channelDTO.getPhotoId());
        entity.setPhotoId(channelDTO.getPhotoId());
        entity.setDescription(channelDTO.getDescription());
        profileService.get(userId);
        entity.setProfileId(userId);
        entity.setStatus(GeneralStatus.ACTIVE);
        entity.setId(UUID.randomUUID().toString());
        channelRepository.save(entity);
        channelDTO.setId(entity.getId());
        return channelDTO;
    }

    public Integer update(String id, ChannelDTO channelDTO) {
        Integer userId = SpringSecurityUtil.getProfileId();
        ChannelEntity entity = get(id);
        if (!entity.getProfileId().equals(userId) || !entity.getStatus().equals(GeneralStatus.ACTIVE)) {
            throw new AppBadRequestException("Channel required");
        }
        entity.setName(channelDTO.getName());
        entity.setDescription(channelDTO.getDescription());
        return channelRepository.update(entity.getName(), entity.getDescription(), id);
    }

    public Boolean updatePhoto(String id, ChannelDTO channelDTO) {
        Integer userId = SpringSecurityUtil.getProfileId();
        ChannelEntity entity = get(id);
        if (!entity.getProfileId().equals(userId) || !entity.getStatus().equals(GeneralStatus.ACTIVE)) {
            throw new AppBadRequestException("Channel required");
        }
        entity.setPhoto(attachService.get(channelDTO.getPhotoId()));
        entity.setPhotoId(channelDTO.getPhotoId());
        return channelRepository.updatePhoto(channelDTO.getPhotoId(), id);
    }

    public Boolean updateBanner(String id, ChannelDTO channelDTO) {
        Integer userId = SpringSecurityUtil.getProfileId();
        ChannelEntity entity = get(id);
        if (!entity.getProfileId().equals(userId) || !entity.getStatus().equals(GeneralStatus.ACTIVE)) {
            throw new AppBadRequestException("Channel required");
        }
        entity.setBanner(attachService.get(channelDTO.getBannerId()));
        entity.setBannerId(channelDTO.getBannerId());
        return channelRepository.updateBanner(channelDTO.getBannerId(), id);
    }

    public ChannelDTO getChanelById(String id) {
        ChannelEntity entity = get(id);
        ChannelDTO dto = toChannelDTO(entity);
        return dto;
    }

    public ChannelEntity get(String id) {
        Optional<ChannelEntity> byId = channelRepository.findById(id);
        if (byId == null) {
            throw new ItemNotFoundException("Channel not found");
        }
        return byId.get();
    }

    public ChannelDTO toChannelDTO(ChannelEntity entity) {
        ChannelDTO dto = new ChannelDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setName(entity.getName());
        dto.setPhotoId(entity.getPhotoId());
        dto.setBannerId(entity.getBannerId());
        return dto;
    }

    public List<ChannelEntity> getChanelByProfileId(Integer profileId) {
        List<ChannelEntity> channels = channelRepository.getChannelByProfileId(profileId);
        if (channels.isEmpty()){
            throw new ItemNotFoundException("Item  not found");
        }
        return channels;
    }
    public Page<ChannelDTO> paginationWithDate(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<ChannelEntity> pageObj = channelRepository.findAll(paging);

        Long totalCount = pageObj.getTotalElements();
        List<ChannelEntity> entityList = pageObj.getContent();
        List<ChannelDTO> dtoList = new LinkedList<>();
        for (ChannelEntity entity : entityList) {
            ChannelDTO dto = toChannelDTO(entity);
            dtoList.add(dto);
        }
        Page<ChannelDTO> response = new PageImpl<ChannelDTO>(dtoList, paging, totalCount);
        return response;
    }
    public Boolean updateStatus(String id){
        Integer userId = SpringSecurityUtil.getProfileId();
        ChannelEntity entity = get(id);
        if (!entity.getProfileId().equals(userId)){
            throw new AppBadRequestException("Channel required");
        }
        if (entity.getStatus().equals(GeneralStatus.ACTIVE)){
             return channelRepository.updateStatus(GeneralStatus.BLOCK,entity.getId());
        }else if (entity.getStatus().equals(GeneralStatus.BLOCK)){
            return channelRepository.updateStatus(GeneralStatus.ACTIVE, entity.getId());
        }
        return false;
    }
    public ChanelVideoLikeRequestDTO getChanelVideoLikeRequestDto(ChannelEntity channel) {
        ChanelVideoLikeRequestDTO dto = new ChanelVideoLikeRequestDTO();
        dto.setId(channel.getId());
        dto.setName(channel.getName());
        return dto;
    }
}
