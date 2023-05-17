package com.example.youtube.service;

import com.example.youtube.dto.profile.ProfileChangePasswordDTO;
import com.example.youtube.dto.profile.ProfileCreateRequestDTO;
import com.example.youtube.dto.profile.ProfileDTO;
import com.example.youtube.entity.ProfileEntity;
import com.example.youtube.enums.GeneralStatus;
import com.example.youtube.exps.AppBadRequestException;
import com.example.youtube.exps.MethodNotAllowedException;
import com.example.youtube.repository.ProfileRepository;
import com.example.youtube.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public Boolean changePassword(ProfileChangePasswordDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(dto.getEmail(), MD5Util.getMd5Hash(dto.getOldPassword()));
        if (optional.isEmpty()){
            throw new MethodNotAllowedException("Password or Email error");
        }
        profileRepository.changePassword(MD5Util.getMd5Hash(dto.getNewPassword()), dto.getEmail());
        return true;
    }

    public Boolean changeDetail(String name, String surname, Integer ownerId) {
        ProfileEntity entity = get(ownerId);
        entity.setName(name);
        entity.setSurname(surname);
        profileRepository.save(entity);
        return true;
    }
    public ProfileEntity get(Integer id){
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Profile not found");
        }
        return optional.get();
    }

    public ProfileDTO getAllDetail(Integer profileId) {
        ProfileEntity entity = get(profileId);
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        return dto;
    }
    public ProfileDTO create(ProfileCreateRequestDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword()));
        entity.setRole(dto.getRole());
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);
        return toDTO(entity);
    }
    private ProfileDTO toDTO(ProfileEntity entity){
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPassword(null);
        dto.setEmail(entity.getEmail());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
