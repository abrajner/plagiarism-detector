package com.abrajner.plagiarismdetector.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abrajner.plagiarismdetector.dao.entity.GroupEntity;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    
    Optional<GroupEntity> getAllByGroupNameAndUserId(final String groupName, final Long userId);
    
    List<GroupEntity> getAllByUserId(final Long userId);
    
    GroupEntity getAllById(final Long groupId);
}
