package com.example.youtube.repository;

import com.example.youtube.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity,String> {
}
