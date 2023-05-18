package com.example.youtube.repository;

import com.example.youtube.entity.PlayListEntity;
import com.example.youtube.enums.VisibleStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface PlaylistRepository extends CrudRepository<PlayListEntity, Integer>,
        PagingAndSortingRepository<PlayListEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update PlayListEntity set status =:status where id =:id")
    int updateStatus(@Param("status") VisibleStatus status, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update PlayListEntity set visible = false where id =:id")
    int deletePlaylist(@Param("id") Integer id);
}
