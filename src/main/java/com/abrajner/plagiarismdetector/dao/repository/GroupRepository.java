package com.abrajner.plagiarismdetector.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abrajner.plagiarismdetector.dao.entity.GroupEntity;
import com.abrajner.plagiarismdetector.dao.entity.UserEntity;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

}
