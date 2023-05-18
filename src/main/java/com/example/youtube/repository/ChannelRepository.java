package com.example.youtube.repository;

import com.example.youtube.entity.ChannelEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends CrudRepository<ChannelEntity, String> {
}
