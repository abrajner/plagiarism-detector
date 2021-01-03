package com.abrajner.plagiarismdetector.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abrajner.plagiarismdetector.dao.compositekey.FileGroupId;
import com.abrajner.plagiarismdetector.dao.entity.FileGroupEntity;

@Repository
public interface FileGroupRepository extends JpaRepository<FileGroupEntity, FileGroupId> {
    
    List<FileGroupEntity> getAllByGroupId(final Long groupEntity);
    
}
