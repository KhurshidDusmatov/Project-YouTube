package com.example.youtube.repository;

import com.example.youtube.entity.CommentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public  interface CommentRepository extends CrudRepository<CommentEntity,Integer>, PagingAndSortingRepository<CommentEntity,Integer> {

    List<CommentEntity> findByProfileId(Integer profileId);
    List<CommentEntity> findByVideoId(String videoId);
    @Query("from CommentEntity where replyPid=:replyPid")
    List<CommentEntity> getByReplyId(@Param("replyPid") Integer repId);
}
