package com.example.youtube.service;

import com.example.youtube.dto.auth.AuthDTO;
import com.example.youtube.dto.auth.AuthResponseDTO;
import com.example.youtube.dto.auth.RegistrationDTO;
import com.example.youtube.dto.auth.RegistrationResponseDTO;
import com.example.youtube.entity.ProfileEntity;
import com.example.youtube.enums.GeneralStatus;
import com.example.youtube.enums.ProfileRole;
import com.example.youtube.exps.AppBadRequestException;
import com.example.youtube.exps.ItemNotFoundException;
import com.example.youtube.repository.ProfileRepository;
import com.example.youtube.util.JwtUtil;
import com.example.youtube.util.MD5Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final ProfileRepository profileRepository;
    private final MailSenderService mailSenderService;

    public RegistrationResponseDTO  registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());

        if (optional.isPresent()) {
            throw new ItemNotFoundException("Email/phone already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword()));
        entity.setSurname(dto.getSurname());
        entity.setName(dto.getName());
        entity.setStatus(GeneralStatus.REGISTER);
        entity.setRole(ProfileRole.ROLE_USER);

        mailSenderService.sendRegistrationEmailMime(dto.getEmail());

        profileRepository.save(entity);
        String s = "Verification link was send to email: " + dto.getEmail();
        return new RegistrationResponseDTO(s);
    }

    public AuthResponseDTO login(AuthDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPasswordAndVisible(
                dto.getEmail(),
                MD5Util.getMd5Hash(dto.getPassword()),
                true);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Email or password incorrect");
        }
        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(GeneralStatus.ACTIVE)) {
            throw new AppBadRequestException("Wrong status");
        }
        AuthResponseDTO responseDTO = new AuthResponseDTO();
        responseDTO.setName(entity.getName());
        responseDTO.setSurname(entity.getSurname());
        responseDTO.setRole(entity.getRole());
        responseDTO.setJwt(JwtUtil.encode(entity.getEmail(), entity.getRole()));
        return responseDTO;
    }
}
