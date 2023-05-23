package com.example.youtube.repository;

import com.example.youtube.entity.ChannelEntity;
import com.example.youtube.enums.GeneralStatus;
import com.sun.mail.imap.protocol.BODY;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository extends CrudRepository<ChannelEntity,String> {
    @Transactional
    @Modifying
    @Query("update ChannelEntity set name=:name, description=:description where id=:id")
    Integer update(@Param("name") String name, @Param("description") String desc, @Param("id") String id);

    @Transactional
    @Modifying
    @Query("update ChannelEntity set photoId=:photo where id=:id")
    Boolean updatePhoto(@Param("photo") String photo, @Param("id") String id);

    @Transactional
    @Modifying
    @Query("update ChannelEntity set bannerId=:photo where id=:id")
    Boolean updateBanner(@Param("photo") String photo, @Param("id") String id);

    @Query("from ChannelEntity where profileId =:profileId")
    List<ChannelEntity> getChannelByProfileId(@Param("profileId") Integer profileId);

    @Transactional
    @Modifying
    @Query("update ChannelEntity set status=:status where id=:id")
    Boolean updateStatus(@Param("status") GeneralStatus status, @Param("id") String id);

    Page<ChannelEntity> findAll(Pageable page);
}
