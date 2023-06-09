package com.example.youtube.repository;

import com.example.youtube.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity,Integer> {

    Optional<SubscriptionEntity> findByProfileIdAndChannelId(Integer profileId, String channelId);

    @Query("select s from SubscriptionEntity as s where s.profileId=:profileId and s.status='ACTIVE'")
    List<SubscriptionEntity> getList(Integer profileId);

}
