package com.example.youtube.service;

import com.example.youtube.dto.profile.ProfileChangePasswordDTO;
import com.example.youtube.entity.ProfileEntity;
import com.example.youtube.exps.MethodNotAllowedException;
import com.example.youtube.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public Boolean changePassword(ProfileChangePasswordDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(dto.getEmail(), dto.getOldPassword());
        if (optional.isEmpty()){
            throw new MethodNotAllowedException("Password or Email error");
        }
        int result = profileRepository.changePassword(dto.getNewPassword(), dto.getEmail());
        return true;
    }
}
