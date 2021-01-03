package com.abrajner.plagiarismdetector.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abrajner.plagiarismdetector.dao.entity.FileEntity;
import com.abrajner.plagiarismdetector.dao.entity.UserEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    
    FileEntity getAllById(final Long fileId);
}
