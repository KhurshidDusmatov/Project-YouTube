package com.example.youtube.repository;

import com.example.youtube.entity.EmailHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity,Integer> {
}
