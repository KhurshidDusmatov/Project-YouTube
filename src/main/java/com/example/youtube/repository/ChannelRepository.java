package com.example.youtube.repository;

import com.example.youtube.entity.ChannelEntity;
import com.sun.mail.imap.protocol.BODY;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChannelRepository extends CrudRepository<ChannelEntity,String> {
    @Transactional
    @Modifying
    @Query("update ChannelEntity set name=:name, description=:description where id=:id")
    Boolean update(@Param("name") String name, @Param("description") String desc, @Param("id") String id);

    @Transactional
    @Modifying
    @Query("update ChannelEntity set photoId=:photo where id=:id")
    Boolean updatePhoto(@Param("photo") String photo, @Param("id") String id);

    @Transactional
    @Modifying
    @Query("update ChannelEntity set bannerId=:photo where id=:id")
    Boolean updateBanner(@Param("photo") String photo, @Param("id") String id);
}
