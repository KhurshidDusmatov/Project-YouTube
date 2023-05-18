package com.example.youtube.repository;

import com.example.youtube.entity.VideoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VideoRepository extends CrudRepository<VideoEntity, String>,
        PagingAndSortingRepository<VideoEntity, String> {
    @Transactional
    @Modifying
    @Query("update VideoEntity set viewCount=:viewCount where id=:id")
    VideoEntity videoViewCountById(@Param("id") String id, @Param("viewCount") Integer viewCount);

    @Query("select v from VideoEntity v where v.id=:id and v.status='PRIVATE'")
    Optional<VideoEntity> getById(@Param("id") String id);
}
