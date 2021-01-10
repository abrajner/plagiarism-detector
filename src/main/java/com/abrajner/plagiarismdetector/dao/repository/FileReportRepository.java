package com.abrajner.plagiarismdetector.dao.repository;

import java.io.File;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abrajner.plagiarismdetector.dao.compositekey.FileReportId;
import com.abrajner.plagiarismdetector.dao.entity.FileReportEntity;
import com.abrajner.plagiarismdetector.dao.entity.UserEntity;

@Repository
public interface FileReportRepository extends JpaRepository<FileReportEntity, FileReportId> {
    List<FileReportEntity> getAllByReportId(final Long reportId);
}
