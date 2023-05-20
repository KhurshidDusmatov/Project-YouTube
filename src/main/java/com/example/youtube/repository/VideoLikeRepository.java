package com.example.youtube.repository;

import com.example.youtube.entity.VideoLikeEntity;
import com.example.youtube.enums.EmotionType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VideoLikeRepository extends CrudRepository<VideoLikeEntity, Integer> {
    Optional<VideoLikeEntity> findByVideoIdAndProfileId(String videoId, Integer profileId);

    @Modifying
    @Transactional
    @Query("update VideoLikeEntity  set type =:type where videoId=:videoId and profileId=:profileId")
    int update(@Param("type") EmotionType type,
               @Param("videoId") String videoId,
               @Param("profileId") Integer profileId);

    @Modifying
    @Transactional
    @Query("delete from VideoLikeEntity where videoId=:videoId and profileId=:profileId")
    int delete(String videoId, Integer profileId);

    @Query("from VideoLikeEntity where profileId = :profileId and type=:emotion order by createdDate desc ")
    List<VideoLikeEntity> getEmotionVideos(@Param("profileId") Integer profileId,@Param("emotion") String emotion);
}
