package com.example.youtube.repository;

import com.example.youtube.entity.VideoTagEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface VideoTagRepository extends CrudRepository<VideoTagEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update VideoTagEntity set visible = false where videoId =:videoId and tagId =:tagId")
    int changeVisible(@Param("videoId") String videoId, @Param("tagId") Integer tagId);
    @Query(value = "select * from video_tag where visible = true and video_id =:videoId", nativeQuery = true)
    List<VideoTagEntity> getVideoTagListByVideoId(@Param("videoId") String videoId);
}
