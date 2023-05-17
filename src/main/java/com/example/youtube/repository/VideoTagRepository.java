package com.example.youtube.repository;

import com.example.youtube.entity.VideoTagEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoTagRepository extends CrudRepository<VideoTagEntity, Integer> {
}
