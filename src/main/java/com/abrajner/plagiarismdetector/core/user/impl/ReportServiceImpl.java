package com.abrajner.plagiarismdetector.core.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abrajner.plagiarismdetector.core.user.ReportService;
import com.abrajner.plagiarismdetector.dao.entity.FileEntity;
import com.abrajner.plagiarismdetector.dao.entity.FileReportEntity;
import com.abrajner.plagiarismdetector.dao.entity.ReportEntity;
import com.abrajner.plagiarismdetector.dao.repository.FileReportRepository;
import com.abrajner.plagiarismdetector.dao.repository.FileRepository;
import com.abrajner.plagiarismdetector.dao.repository.ReportRepository;
import com.abrajner.plagiarismdetector.gui.dto.ReportsResponse;
import com.abrajner.plagiarismdetector.gui.dto.SingleReport;

@Service
public class ReportServiceImpl implements ReportService {
    
    final ReportRepository reportRepository;
    
    final FileReportRepository fileReportRepository;
    
    final FileRepository fileRepository;
    
    public ReportServiceImpl(final ReportRepository reportRepository,
                             final FileReportRepository fileReportRepository,
                             final FileRepository fileRepository) {
        this.reportRepository = reportRepository;
        this.fileReportRepository = fileReportRepository;
        this.fileRepository = fileRepository;
    }
    
    @Override
    public boolean isReportGenerationFinished(final Long groupId, final String reportName) {
        final AtomicBoolean isFinished = new AtomicBoolean(true);
        this.reportRepository.getAllByGroupIdAndReportName(groupId, reportName).forEach(report -> {
            if(!report.isFinished()){
                isFinished.set(false);
            }
        });
        return isFinished.get();
    }
    
    @Override
    public List<ReportsResponse> getAllReportsForGroup(final Long groupId) {
        final List<ReportsResponse> result = new ArrayList<>();
        final List<ReportEntity> allReportsForGroup = this.reportRepository.getAllByGroupId(groupId);
        final Map<String, List<ReportEntity>> reportsGroupedByReportName = this.groupReportsByReportName(allReportsForGroup);
        reportsGroupedByReportName.forEach((key, value) -> {
            final ReportsResponse reportsResponse = new ReportsResponse();
            reportsResponse.setReportName(key);
            final boolean isFinished = this.isReportGenerationFinished(groupId, key);
            reportsResponse.setFinished(isFinished);
            if(isFinished) {
                final List<SingleReport> reports = new ArrayList<>();
                value.forEach(reportEntity -> {
                    final SingleReport singleReport = new SingleReport();
                    singleReport.setCodeSimilarityPercentage(reportEntity.getCodeSimilarityPercentage());
                    final List<FileEntity> filesInReport = this.getAllFilesForReport(reportEntity.getId());
                    singleReport.setFirstFileId(filesInReport.get(0).getId());
                    singleReport.setFirstFileName(filesInReport.get(0).getFileName());
                    singleReport.setFirstFileAuthor(filesInReport.get(0).getFileAuthor());
                    singleReport.setSecondFileId(filesInReport.get(1).getId());
                    singleReport.setSecondFileName(filesInReport.get(1).getFileName());
                    singleReport.setSecondFileAuthor(filesInReport.get(1).getFileAuthor());
                    singleReport.setSubstitutionIncluded(reportEntity.getSubstitutionIncluded());
                    singleReport.setPlagiarism(reportEntity.getPlagiarism());
                    singleReport.setCodeSimilarityPercentageWithSubstitution(reportEntity.getCodeSimilarityPercentageWithSubstitution());
                    reports.add(singleReport);
                });
                reportsResponse.setReportsForFiles(reports);
            }
            result.add(reportsResponse);
        });
        return result;
    }
    
    private Map<String, List<ReportEntity>> groupReportsByReportName(final List<ReportEntity> allReports){
        final Map<String, List<ReportEntity>> reportsGroupedByReportName = new HashMap<>();
        allReports.forEach(report -> {
            if(!reportsGroupedByReportName.containsKey(report.getReportName())){
                reportsGroupedByReportName.put(report.getReportName(), new ArrayList<>());
            }
            reportsGroupedByReportName.get(report.getReportName()).add(report);
        });
        return reportsGroupedByReportName;
    }
    
    private List<FileEntity> getAllFilesForReport(final Long reportId){
        final List<Long> allFilesForReport = this.fileReportRepository.getAllByReportId(reportId)
                .stream().map(FileReportEntity::getFileId).collect(Collectors.toList());
        return this.fileRepository.getAllByIdIsIn(allFilesForReport);
    }
}
