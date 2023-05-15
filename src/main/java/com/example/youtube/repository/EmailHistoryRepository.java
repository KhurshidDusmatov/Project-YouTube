package com.example.youtube.repository;

import com.example.youtube.entity.EmailHistoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity,Integer> {
}
