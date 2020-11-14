package com.abrajner.plagiarismdetector.dao.repository;

import java.io.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abrajner.plagiarismdetector.dao.entity.FileReportEntity;
import com.abrajner.plagiarismdetector.dao.entity.UserEntity;

@Repository
public interface FileReportRepository extends JpaRepository<FileReportEntity, Long> {

}
