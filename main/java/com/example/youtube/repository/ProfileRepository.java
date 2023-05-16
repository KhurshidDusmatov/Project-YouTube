package com.example.youtube.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import com.example.youtube.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {

    @Query("from ProfileEntity where email =:email and password =:password")
    Optional<ProfileEntity> findByEmailAndPassword(@Param("email") String email, @Param("password") String oldPassword);

    @Transactional
    @Modifying
    @Query("update ProfileEntity  set password =:password where email =:email")
    int changePassword(@Param("password") String newPassword, @Param("email") String email);
}
