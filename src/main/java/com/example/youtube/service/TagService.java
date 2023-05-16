package com.example.youtube.service;

import com.example.youtube.dto.tag.TagRequestDTO;
import com.example.youtube.dto.tag.TagResponseDTO;
import com.example.youtube.entity.TagEntity;
import com.example.youtube.exps.ItemNotFoundException;
import com.example.youtube.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public TagResponseDTO create(TagRequestDTO dto) {
        TagEntity entity = new TagEntity();
        entity.setName(dto.getName());
        tagRepository.save(entity);
        return toResponseDTO(entity);
    }

    public TagResponseDTO toResponseDTO(TagEntity entity) {
        TagResponseDTO dto = new TagResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public TagResponseDTO update(Integer id, TagRequestDTO dto) {
        TagEntity entity = get(id);
        tagRepository.update(id, dto.getName());
        entity.setName(dto.getName());
        return toResponseDTO(entity);
    }

    public Boolean delete(Integer id) {
        TagEntity entity = get(id);
        return tagRepository.delete(id) == 1;
    }

    public TagEntity get(Integer id) {
        Optional<TagEntity> optional = tagRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("not fount tag ");
        }
        return optional.get();
    }

    public List<TagResponseDTO> getList() {
        List<TagEntity> entityList = tagRepository.getList();
        List<TagResponseDTO> list = new LinkedList<>();
        entityList.forEach(entity -> list.add(toResponseDTO(entity)));
        return list;
    }
}
