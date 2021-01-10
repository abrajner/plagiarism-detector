package com.abrajner.plagiarismdetector.core.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abrajner.plagiarismdetector.core.user.AnalysisService;
import com.abrajner.plagiarismdetector.dao.entity.FileEntity;
import com.abrajner.plagiarismdetector.dao.entity.FileReportEntity;
import com.abrajner.plagiarismdetector.dao.entity.ReportEntity;
import com.abrajner.plagiarismdetector.dao.repository.FileReportRepository;
import com.abrajner.plagiarismdetector.dao.repository.FileRepository;
import com.abrajner.plagiarismdetector.dao.repository.ReportRepository;
import com.abrajner.plagiarismdetector.fileparser.TokenizedStringSerializer;
import com.abrajner.plagiarismdetector.plagiarismanalysis.FileAnalysis;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    
    final ReportRepository reportRepository;
    
    final FileReportRepository fileReportRepository;
    
    final FileRepository fileRepository;
    
    public AnalysisServiceImpl(final ReportRepository reportRepository,
                               final FileReportRepository fileReportRepository,
                               final FileRepository fileRepository) {
        this.reportRepository = reportRepository;
        this.fileReportRepository = fileReportRepository;
        this.fileRepository = fileRepository;
    }
    
    @Override
    public void startAnalysis(final List<Long> fileIds, final String reportName, final Long groupId) {
        final List<List<Long>> filesCombination = this.allFileCombinations(fileIds);
        filesCombination.forEach(combination -> {
            final ReportEntity reportEntity = this.reportRepository.save(new ReportEntity.Builder()
                    .isFinished(false)
                    .reportName(reportName)
                    .groupId(groupId).build());
            
            combination.forEach(singleCombinationsFile -> {
                this.fileReportRepository.save(new FileReportEntity.Builder()
                        .fileId(singleCombinationsFile)
                        .reportId(reportEntity.getId()).build());
            });
        });
        this.performAnalysis(groupId, reportName);
    }
    
    @Override
    public void performAnalysis(final Long groupId, final String reportName) {
        final FileAnalysis fileAnalysis = new FileAnalysis();
        final List<ReportEntity> reportEntities = this.reportRepository.getAllByGroupIdAndReportName(groupId, reportName);
        reportEntities.forEach(reportEntity -> {
            final List<FileReportEntity> fileReportEntities = this.fileReportRepository.getAllByReportId(reportEntity.getId());
            final List<Long> fileIds = new ArrayList<>();
            fileReportEntities.forEach(fileReportEntity -> {
                fileIds.add(fileReportEntity.getFileId());
            });
            final List<FileEntity> filesToAnalyse = this.fileRepository.getAllByIdIsIn(fileIds);
            final List<List<String>> firstFileDeserialized = this.deserializeFileContentFromDatabase(filesToAnalyse.get(0).getParsedFileContent());
            final List<List<String>> secondFileDeserialized = this.deserializeFileContentFromDatabase(filesToAnalyse.get(1).getParsedFileContent());
            final Double codeSimilarityPercentage = fileAnalysis.analyze(
                    firstFileDeserialized,
                    this.deserializeIdentifiersFromDatabase(filesToAnalyse.get(0).getIdentifiers()),
                    secondFileDeserialized,
                    this.deserializeIdentifiersFromDatabase(filesToAnalyse.get(1).getIdentifiers()),
                    false);
            Double codeSimilarityPercentageWithSubstitution = 0.0;
            boolean isSubstitutionIncluded = false;
            if(!fileAnalysis.getTokensForSubstitution().isEmpty()){
                isSubstitutionIncluded = true;
                if(firstFileDeserialized.size() >= secondFileDeserialized.size()){
                    codeSimilarityPercentageWithSubstitution = fileAnalysis.analyze(
                            this.replaceValues(fileAnalysis.getTokensForSubstitution(), firstFileDeserialized),
                            this.deserializeIdentifiersFromDatabase(filesToAnalyse.get(0).getIdentifiers()),
                            secondFileDeserialized,
                            this.deserializeIdentifiersFromDatabase(filesToAnalyse.get(1).getIdentifiers()),
                            true);
                }
                else{
                    if(firstFileDeserialized.size() >= secondFileDeserialized.size()){
                        codeSimilarityPercentageWithSubstitution = fileAnalysis.analyze(
                                firstFileDeserialized,
                                this.deserializeIdentifiersFromDatabase(filesToAnalyse.get(0).getIdentifiers()),
                                this.replaceValues(fileAnalysis.getTokensForSubstitution(), secondFileDeserialized),
                                this.deserializeIdentifiersFromDatabase(filesToAnalyse.get(1).getIdentifiers()),
                                true);
                    }
                }
            }
            reportEntity.setSubstitutionIncluded(isSubstitutionIncluded);
            reportEntity.setFinished(true);
            reportEntity.setCodeSimilarityPercentageWithSubstitution(codeSimilarityPercentageWithSubstitution * 100);
            reportEntity.setCodeSimilarityPercentage(codeSimilarityPercentage * 100);
            reportEntity.setPlagiarism(codeSimilarityPercentage >= 0.5);
            this.reportRepository.save(reportEntity);
        });
    }
    
    private List<List<Long>> allFileCombinations(final List<Long> fileIds){
        final List<List<Long>> filesCombination = new ArrayList<>();
        fileIds.forEach(file -> {
            fileIds.forEach(secondFileId -> {
                if(!file.equals(secondFileId) && (filesCombination.isEmpty()
                        || filesCombination.stream().noneMatch(combination -> combination.contains(file) && combination.contains(secondFileId)))){
                    List<Long> list = new ArrayList<>();
                    list.add(file);
                    list.add(secondFileId);
                    filesCombination.add(list);
                }
            });
        });
        return filesCombination;
    }
    
    private List<List<String>> deserializeFileContentFromDatabase(final String serializedData){
        return TokenizedStringSerializer.deserializeTokens(serializedData);
    }
    
    private List<String> deserializeIdentifiersFromDatabase(final String serializedData){
        return TokenizedStringSerializer.deserializeIdentifiers(serializedData);
    }
    
    private List<List<String>> replaceValues(final Map<String, String> tokensForSubstitution, final List<List<String>> fileContent){
        return fileContent.stream().peek(line ->
                tokensForSubstitution.forEach((key, value) -> {
                    line.replaceAll(x -> x.replace(key, value));
                })).collect(Collectors.toList());
    }
}
