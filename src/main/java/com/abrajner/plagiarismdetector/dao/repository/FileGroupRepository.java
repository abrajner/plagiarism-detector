package com.abrajner.plagiarismdetector.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abrajner.plagiarismdetector.dao.entity.FileGroupEntity;
import com.abrajner.plagiarismdetector.dao.entity.UserEntity;

@Repository
public interface FileGroupRepository extends JpaRepository<FileGroupEntity, Long> {

}
