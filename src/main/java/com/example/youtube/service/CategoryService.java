package com.example.youtube.service;

import com.example.youtube.dto.category.CategoryRequestDTO;
import com.example.youtube.entity.CategoryEntity;
import com.example.youtube.exps.CategoryNotFoundException;
import com.example.youtube.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public boolean create(CategoryRequestDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.getName());
        categoryRepository.save(entity);
        return true;
    }

    public boolean update(String id, String name) {
        CategoryEntity entity = get(id);
        entity.setName(name);
        categoryRepository.save(entity);
        return true;
    }

    public CategoryEntity get(String id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException("Category not found !!!");
        }
        return optional.get();
    }

    public boolean delete(String id) {
        CategoryEntity entity = get(id);
        categoryRepository.delete(entity);
        return true;
    }

    public List<CategoryRequestDTO> getAll() {
        List<CategoryRequestDTO> response = new LinkedList<>();
        categoryRepository.findAll().forEach(categoryEntity -> {
            CategoryRequestDTO dto = new CategoryRequestDTO();
            dto.setName(categoryEntity.getName());
            response.add(dto);
        });
        return response;
    }
}
