package com.example.youtube.service;

import com.example.youtube.dto.comment.CommentFullResponseDTO;
import com.example.youtube.dto.comment.CommentInfoDTO;
import com.example.youtube.dto.comment.CommentRequestDTO;
import com.example.youtube.dto.comment.CommentResponseDTO;
import com.example.youtube.entity.CommentEntity;
import com.example.youtube.exps.CommentNotFoundException;
import com.example.youtube.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public boolean create(CommentRequestDTO dto, Integer pid, String attachId) {
        CommentEntity entity = new CommentEntity();
        entity.setContent(dto.getContent());
        entity.setProfileId(pid);
        entity.setVideoId(attachId);
        commentRepository.save(entity);
        return true;
    }
    public boolean update(Integer commentId, String content) {
        CommentEntity entity = get(commentId);
        entity.setContent(content);
        commentRepository.save(entity);
        return true;
    }
    public CommentEntity get(Integer commentId) {
        Optional<CommentEntity> optional = commentRepository.findById(commentId);
        if (optional.isEmpty()) {
            throw new CommentNotFoundException("Comment not found ???");
        }
        return optional.get();
    }
    public boolean delete(Integer commentId) {
        CommentEntity entity = get(commentId);
        commentRepository.delete(entity);
        return true;
    }
    public Page<CommentResponseDTO> paging(int page, int size) {
        Sort sort = Sort.by(Sort.DEFAULT_DIRECTION, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CommentEntity> response = commentRepository.findAll(pageable);
        long totalCount = response.getTotalElements();
        List<CommentResponseDTO> dtoList = new LinkedList<>();
        List<CommentEntity> entityList = response.getContent();
        entityList.forEach(entity -> {
            CommentResponseDTO dto = new CommentResponseDTO();
            dto.setContent(entity.getContent());
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setProfileId(entity.getProfileId());
            dto.setLike_count(entity.getLikeCount());
            dto.setVideoId(entity.getVideoId());
            dto.setDislike_count(entity.getDislikeCount());
            dto.setReplyPid(entity.getReplyPid());
            dtoList.add(dto);
        });
        return new PageImpl<CommentResponseDTO>(dtoList, pageable, totalCount);
    }
    public List<CommentFullResponseDTO> getByPid(Integer profileId) {
        List<CommentFullResponseDTO> list = new LinkedList<>();
        getByProfileId(profileId).forEach(commentEntity -> {
            CommentFullResponseDTO dto = new CommentFullResponseDTO();
            dto.setId(commentEntity.getId());
            dto.setContent(commentEntity.getContent());
            dto.setCreatedDate(commentEntity.getCreatedDate());
            dto.setLikeCount(commentEntity.getLikeCount());
            dto.setDislikeCount(commentEntity.getDislikeCount());

            // TODO: 5/18/2023  // video
        });
        return list;

        //id,content,created_date,like_count,dislike_count, video(id,name,preview_attach_id,title,duration)
    }
    public List<CommentEntity> getByProfileId(Integer pid) {
        List<CommentEntity> entityList = commentRepository.findByProfileId(pid);
        if (entityList.isEmpty()) {
            throw new CommentNotFoundException("This user has not commented");
        }
        return entityList;
    }
    public List<CommentInfoDTO> getByVideoId(String vId) {
        List<CommentInfoDTO> response = new LinkedList<>();
        getByVidId(vId).forEach(entity -> {
            CommentInfoDTO dto = new CommentInfoDTO();
            dto.setContent(entity.getContent());
            dto.setProfile(null); // TODO: 5/18/2023
            dto.setId(entity.getId());
            dto.setLikeCount(entity.getLikeCount());
            dto.setDislikeCount(entity.getDislikeCount());
            dto.setCreatedDate(entity.getCreatedDate());
            response.add(dto);
        });

        return response;
    }
    public List<CommentEntity> getByVidId(String vId) {
        List<CommentEntity> entityList = commentRepository.findByVideoId(vId);
        if (entityList.isEmpty()) {
            throw new CommentNotFoundException("Not found Comment this video ");
        }
        return entityList;
    }
    public List<CommentInfoDTO> getByRepliedId(Integer repId) {
        List<CommentInfoDTO> response = new LinkedList<>();
        getByRepId(repId).forEach(entity -> {
            CommentInfoDTO dto = new CommentInfoDTO();
            dto.setContent(entity.getContent());
            dto.setProfile(null); // TODO: 6/18/2023
            dto.setId(entity.getId());
            dto.setLikeCount(entity.getLikeCount());
            dto.setDislikeCount(entity.getDislikeCount());
            dto.setCreatedDate(entity.getCreatedDate());
            response.add(dto);
        });
        return response;
    }
    public List<CommentEntity> getByRepId(Integer rId) {
        List<CommentEntity> entityList = commentRepository.getByReplyId(rId);
        if (entityList.isEmpty()) {
            throw new CommentNotFoundException("Not found reply Comment this video ");
        }
        return entityList;
    }
}
