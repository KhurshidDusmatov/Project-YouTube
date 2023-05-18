package com.example.youtube.service;

import com.example.youtube.config.security.CustomUserDetails;
import com.example.youtube.config.security.CustomUserDetailsService;
import com.example.youtube.dto.channel.ChannelDTO;
import com.example.youtube.entity.ChannelEntity;
import com.example.youtube.enums.GeneralStatus;
import com.example.youtube.exps.AppBadRequestException;
import com.example.youtube.exps.ItemNotFoundException;
import com.example.youtube.repository.ChannelRepository;
import com.example.youtube.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
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
        entity.setBanner(attachService.get(channelDTO.getBannerId()));
        entity.setBannerId(channelDTO.getBannerId());
        entity.setPhoto(attachService.get(channelDTO.getPhotoId()));
        entity.setPhotoId(channelDTO.getPhotoId());
        entity.setDescription(channelDTO.getDescription());
        entity.setProfile(profileService.get(userId));
        entity.setProfileId(userId);
        entity.setStatus(GeneralStatus.ACTIVE);
        entity.setId(UUID.randomUUID().toString());
        channelRepository.save(entity);
        channelDTO.setId(entity.getId());
        return channelDTO;
    }

    public Boolean update(String id, ChannelDTO channelDTO) {
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
        ChannelDTO dto = new ChannelDTO();
        return null;
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
        return null;
    }

}
