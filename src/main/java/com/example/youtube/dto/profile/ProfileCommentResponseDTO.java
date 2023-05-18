package com.example.youtube.dto.profile;

import com.example.youtube.dto.attach.AttachCommentResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileCommentResponseDTO {
    private Integer profileId;
    private String name;
    private String surname;
    private AttachCommentResponseDTO photo;
}
//  profile(id,name,surname,photo(id,url))