package com.abrajner.plagiarismdetector.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abrajner.plagiarismdetector.dao.entity.ReportEntity;
import com.abrajner.plagiarismdetector.dao.entity.UserEntity;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
    
    List<ReportEntity> getAllByGroupIdAndReportName(final Long groupId, final String reportName);
    
    List<ReportEntity> getAllByGroupId(final Long groupId);
}
