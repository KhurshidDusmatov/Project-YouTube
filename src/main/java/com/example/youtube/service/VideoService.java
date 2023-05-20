package com.example.youtube.service;

import com.example.youtube.dto.tag.TagResponseDTO;
import com.example.youtube.dto.video.*;
import com.example.youtube.entity.VideoEntity;
import com.example.youtube.enums.VideoStatus;
import com.example.youtube.exps.VideoNotFoundException;
import com.example.youtube.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;
    private final AttachService attachService;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final PlaylistService playlistService;

    private final ProfileService profileService;

    public VideoEntity get(String id) {
        Optional<VideoEntity> optional = videoRepository.findById(id);
        if (optional.isEmpty()) {
            throw new VideoNotFoundException("video not found");
        }
        return optional.get();
    }

    public VideoResponseDTO create(VideoRequestDTO dto) {
        VideoEntity entity = new VideoEntity();
        entity.setAttachId(dto.getAttachId());
        entity.setDescription(dto.getDescription());
        entity.setChannelId(dto.getChannelId());
        entity.setPreviewAttachId(dto.getPreviewAttachId());
        entity.setTitle(dto.getTitle());
        entity.setCategoryId(dto.getCategoryId());
        entity.setStatus(dto.getStatus());
        entity.setType(dto.getType());
        videoRepository.save(entity);
        return toResponseDTO(entity);
    }

    private VideoResponseDTO toResponseDTO(VideoEntity entity) {
        VideoResponseDTO dto = new VideoResponseDTO();
        dto.setId(entity.getId());
        dto.setAttachId(entity.getAttachId());
        dto.setPreviewAttachId(entity.getPreviewAttachId());
        dto.setStatus(entity.getStatus());
        dto.setDescription(entity.getDescription());
        dto.setCategoryId(entity.getCategoryId());
        dto.setType(entity.getType());
        dto.setTitle(entity.getTitle());
        dto.setChannelId(entity.getChannelId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public VideoResponseDTO changeStatus(String id) {
        VideoEntity entity = get(id);
        if (entity.getStatus().equals(VideoStatus.PUBLIC)) {
            entity.setStatus(VideoStatus.PRIVATE);
        } else {
            entity.setStatus(VideoStatus.PUBLIC);
        }
        videoRepository.save(entity);
        return toResponseDTO(entity);
    }

    public Integer videoViewCountById(String id) {
        VideoEntity entity = get(id);
        int video = videoRepository.videoViewCountById(id, entity.getViewCount() + 1);
        return video;
    }

    public Page<VideoShortInfo> videoPagingByCategoryId(String categoryId, int size, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<VideoEntity> videoPaging = videoRepository.findAll(pageable);
        List<VideoEntity> entityList = videoPaging.getContent();
        List<VideoShortInfo> list = new LinkedList<>();
        for (VideoEntity entity : entityList) {
            if (entity.getCategoryId().equals(categoryId)) {
                list.add(toShortInfo(entity));
            }
        }
        long totalCount = list.size();
        return new PageImpl<>(list, pageable, totalCount);
    }

    private VideoShortInfo toShortInfo(VideoEntity entity) {
        VideoShortInfo dto = new VideoShortInfo();
        dto.setId(entity.getId());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setViewCount(entity.getViewCount());
//        dto.setPreviewAttach(attachService.toResponseDTO(entity.getPreviewAttach()));
//        dto.setChannel();
        return dto;
    }

    public List<VideoShortInfo> getVideoList(String title) {
        Iterable<VideoEntity> optional = videoRepository.findAll();
        List<VideoShortInfo> list = new LinkedList<>();
        for (VideoEntity entity : optional) {
            if (entity.getTitle().contains(title)) {
                list.add(toShortInfo(entity));
            }
        }
        return list;
    }

    public VideoFullInfo getById(String id) {
        Optional<VideoEntity> optional = videoRepository.getById(id);
        if (optional.isEmpty()) {
            throw new VideoNotFoundException("not found video");
        }

        VideoEntity entity = optional.get();
        VideoFullInfo video = new VideoFullInfo();
        video.setId(entity.getId());
        video.setDescription(entity.getDescription());
        video.setPreviewAttach(attachService.toResponseDTO(entity.getPreviewAttach()));
        video.setAttach(attachService.toResponseDTO(entity.getAttach()));
        video.setCategory(categoryService.toResponseDTO(entity.getCategory()));

        List<TagResponseDTO> dtoList = new LinkedList<>();
        entity.getTagList().forEach(entity1 -> dtoList.add(tagService.toResponseDTO(entity1)));
        video.setTagList(dtoList);

        video.setPublishedDate(entity.getPublishedDate());
        // TODO: 5/18/2023 channel
        video.setViewCount(entity.getViewCount());
        video.setSharedCount(entity.getSharedCount());
        return video;
    }

    public Page<VideoDTO> pagingAdmin(int size, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<VideoEntity> entityPage = videoRepository.findAll(pageable);
        long totalCount = entityPage.getTotalElements();
        List<VideoEntity> entityList = entityPage.getContent();
        List<VideoDTO> dtoList = new LinkedList<>();
        for (VideoEntity entity : entityList) {
            VideoDTO dto = new VideoDTO();
//            dto.setOwner(profileService.toResponseDTO(entity.getChannel().getProfile()));
            dto.setVideoShortInfo(toShortInfo(entity));
//            dto.setPlayList(playlistService.toPlayResponseDTO());
            // TODO: 5/18/2023 playList
            dtoList.add(dto);
        }
        return new PageImpl<>(dtoList, pageable, totalCount);
    }

    public Page<VideoPlayListInfo> paging(int size, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<VideoEntity> entityPage = videoRepository.findAll(pageable);
        long totalCount = entityPage.getTotalElements();
        List<VideoEntity> entityList = entityPage.getContent();
        List<VideoPlayListInfo> list = new LinkedList<>();
        for (VideoEntity entity : entityList) {
            VideoPlayListInfo videoPlayListInfo = new VideoPlayListInfo();
            videoPlayListInfo.setTitle(entity.getTitle());
            videoPlayListInfo.setPreviewAttach(attachService.toResponseDTO(entity.getPreviewAttach()));
            videoPlayListInfo.setViewCount(entity.getViewCount());
            videoPlayListInfo.setPublishedDate(entity.getPublishedDate());
            videoPlayListInfo.setViewCount(entity.getViewCount());
            list.add(videoPlayListInfo);
        }
        return new PageImpl<>(list, pageable, totalCount);
    }
}
