package com.abrajner.plagiarismdetector.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abrajner.plagiarismdetector.dao.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findAllByLoginAndPassword(final String login, final String password);

    Optional<UserEntity> findAllById(final Long id);
}
