package com.abrajner.plagiarismdetector.core.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.abrajner.plagiarismdetector.core.user.AnalysisService;
import com.abrajner.plagiarismdetector.dao.entity.FileEntity;
import com.abrajner.plagiarismdetector.dao.entity.FileReportEntity;
import com.abrajner.plagiarismdetector.dao.entity.ReportEntity;
import com.abrajner.plagiarismdetector.dao.repository.FileReportRepository;
import com.abrajner.plagiarismdetector.dao.repository.FileRepository;
import com.abrajner.plagiarismdetector.dao.repository.ReportRepository;
import com.abrajner.plagiarismdetector.fileparser.ParsedFile;
import com.abrajner.plagiarismdetector.fileparser.TokenizedStringSerializer;
import com.abrajner.plagiarismdetector.plagiarismanalysis.functioncomparator.AnalyzeEveryFunction;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    
    final ReportRepository reportRepository;
    
    final FileReportRepository fileReportRepository;
    
    final FileRepository fileRepository;
    
    final AnalyzeEveryFunction analyzeEveryFunction;
    
    public AnalysisServiceImpl(final ReportRepository reportRepository,
                               final FileReportRepository fileReportRepository,
                               final FileRepository fileRepository,
                               final AnalyzeEveryFunction analyzeEveryFunction) {
        this.reportRepository = reportRepository;
        this.fileReportRepository = fileReportRepository;
        this.fileRepository = fileRepository;
        this.analyzeEveryFunction = analyzeEveryFunction;
    }
    
    @Override
    public void startAnalysis(final List<Long> fileIds, final String reportName, final Long groupId) {
        final List<List<Long>> filesCombination = this.allFileCombinations(fileIds);
        filesCombination.forEach(combination -> {
            final ReportEntity reportEntity = this.reportRepository.save(new ReportEntity.Builder()
                    .isFinished(false)
                    .reportName(reportName)
                    .groupId(groupId).build());
            
            combination.forEach(singleCombinationsFile -> this.fileReportRepository.save(new FileReportEntity.Builder()
                    .fileId(singleCombinationsFile)
                    .reportId(reportEntity.getId()).build()));
        });
        this.performAnalysis(groupId, reportName);
    }
    
    @Override
    public void performAnalysis(final Long groupId, final String reportName) {
        final List<ReportEntity> reportEntities = this.reportRepository.getAllByGroupIdAndReportName(groupId, reportName);
        
        reportEntities.forEach(reportEntity -> {
            final List<FileReportEntity> fileReportEntities = this.fileReportRepository.getAllByReportId(reportEntity.getId());
            final List<Long> fileIds = new ArrayList<>();
            fileReportEntities.forEach(fileReportEntity -> fileIds.add(fileReportEntity.getFileId()));
            final List<FileEntity> filesToAnalyse = this.fileRepository.getAllByIdIsIn(fileIds);
            final ParsedFile firstFileDeserialized = this.deserializeFileContentFromDatabase(filesToAnalyse.get(0).getParsedFileContent());
            final ParsedFile secondFileDeserialized = this.deserializeFileContentFromDatabase(filesToAnalyse.get(1).getParsedFileContent());
            
            final ReportEntity reportWithCalculatedResults = this.analyzeEveryFunction.performAnalysis(
                    firstFileDeserialized,
                    secondFileDeserialized);
            
            reportWithCalculatedResults.setId(reportEntity.getId());
            reportWithCalculatedResults.setGroupId(reportEntity.getGroupId());
            reportWithCalculatedResults.setReportName(reportEntity.getReportName());
            this.reportRepository.save(reportWithCalculatedResults);
        });
    }
    
    private List<List<Long>> allFileCombinations(final List<Long> fileIds){
        final List<List<Long>> filesCombination = new ArrayList<>();
        fileIds.forEach(file -> fileIds.forEach(secondFileId -> {
            if(!file.equals(secondFileId) && (filesCombination.isEmpty()
                    || filesCombination.stream().noneMatch(combination -> combination.contains(file) && combination.contains(secondFileId)))){
                final List<Long> list = new ArrayList<>();
                list.add(file);
                list.add(secondFileId);
                filesCombination.add(list);
            }
        }));
        return filesCombination;
    }
    
    private ParsedFile deserializeFileContentFromDatabase(final String serializedData){
        return TokenizedStringSerializer.deserialize(serializedData);
    }
}
