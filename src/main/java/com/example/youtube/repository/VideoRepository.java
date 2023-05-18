package com.example.youtube.repository;

import com.example.youtube.entity.VideoEntity;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<VideoEntity, String> {
}
