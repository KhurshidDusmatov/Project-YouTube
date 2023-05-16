package com.example.youtube.repository;

import com.example.youtube.entity.TagEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends CrudRepository<TagEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update TagEntity set name=:name where id=:id")
    int update(@Param("id") Integer id, @Param("name") String name);

    @Transactional
    @Modifying
    @Query("update TagEntity set visible=false where id=:id")
    int delete(@Param("id") Integer id);

    @Query("select t from TagEntity t where t.visible = true ")
    List<TagEntity> getList();
}
