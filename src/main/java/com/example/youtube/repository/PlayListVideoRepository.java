package com.example.youtube.repository;

import com.example.youtube.entity.PlayListVideoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PlayListVideoRepository extends CrudRepository<PlayListVideoEntity, Integer> {

    @Query(value = "select count(video_id) from playlist_video where playlist_id =:playlistId", nativeQuery = true)
    int getCountVideo(@Param("playlistId") Integer pId);

    @Query(value = "select created_date from playlist_video where playlist_id =:playlistId  order by created_date desc", nativeQuery = true)
    LocalDateTime getLastUpdateDateByPlayListId(@Param("playlistId") Integer playlistId);

    @Query(value = "select video_id from playlist_video where playlist_id =:playlistId", nativeQuery = true)
    List<String> getVideoIdListByPlaylistId(@Param("playlistId") Integer playlistId);
}
