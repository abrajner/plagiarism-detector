package com.abrajner.plagiarismdetector.applicationservice.impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abrajner.plagiarismdetector.applicationservice.FileInGroupManagementApplicationService;
import com.abrajner.plagiarismdetector.core.user.FileManagementService;
import com.abrajner.plagiarismdetector.gui.dto.FileReducedDto;
import com.abrajner.plagiarismdetector.gui.dto.InputFileDto;
import com.abrajner.plagiarismdetector.mapper.FileMapper;

@Service
public class FileInGroupManagementApplicationServiceImpl implements FileInGroupManagementApplicationService {
    
    final FileManagementService fileManagementService;
    
    final FileMapper fileMapper;
    
    Logger LOGGER = Logger.getLogger(this.getClass().getName());
    
    public FileInGroupManagementApplicationServiceImpl(final FileManagementService fileManagementService,
                                                       final FileMapper fileMapper) {
        this.fileManagementService = fileManagementService;
        this.fileMapper = fileMapper;
    }
    
    @Override
    public List<FileReducedDto> getAllFilesFromGroup(final Long groupId) {
        return this.fileManagementService.getAllActiveFilesInGroup(groupId).stream()
                .map(this.fileMapper::convertToReducedDto)
                .collect(Collectors.toList());
    }
    
    public FileReducedDto validateAndSaveNewFile(final Long groupId, final Long userId, final InputFileDto fileDto, final MultipartFile multipartFile){
         return this.fileMapper.convertToReducedDto(this.fileManagementService.saveNewFile(groupId, userId, fileDto, multipartFile));
    }
    
    @Override
    public Resource downloadFileContent(final Long fileId) {
        return this.fileManagementService.loadFileAsResource(fileId);
    }
    
    
}
