package com.abrajner.plagiarismdetector.core.user.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abrajner.plagiarismdetector.core.user.FileManagementService;
import com.abrajner.plagiarismdetector.dao.entity.FileEntity;
import com.abrajner.plagiarismdetector.dao.entity.FileGroupEntity;
import com.abrajner.plagiarismdetector.dao.entity.GroupEntity;
import com.abrajner.plagiarismdetector.dao.repository.FileGroupRepository;
import com.abrajner.plagiarismdetector.dao.repository.FileRepository;
import com.abrajner.plagiarismdetector.dao.repository.GroupRepository;
import com.abrajner.plagiarismdetector.fileparser.FileTokenizer;
import com.abrajner.plagiarismdetector.fileparser.ProgrammingLanguage;
import com.abrajner.plagiarismdetector.fileparser.TokenizedStringSerializer;
import com.abrajner.plagiarismdetector.gui.dto.InputFileDto;
import com.abrajner.plagiarismdetector.mapper.FileMapper;
import com.abrajner.plagiarismdetector.restapi.controller.error.FileNotFoundException;
import com.abrajner.plagiarismdetector.restapi.controller.error.FileStorageException;

@Service
@PropertySource("classpath:application.properties")
public class FileManagementServiceImpl implements FileManagementService {
    
    final FileGroupRepository fileGroupRepository;
    
    final GroupRepository groupRepository;
    
    final FileRepository fileRepository;
    
    final FileMapper fileMapper;
    
    @Autowired
    ServletContext context;
    
    public FileManagementServiceImpl(final FileGroupRepository fileGroupRepository,
                                     final GroupRepository groupRepository,
                                     final FileRepository fileRepository,
                                     final FileMapper fileMapper) {
        this.fileGroupRepository = fileGroupRepository;
        this.groupRepository = groupRepository;
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
    }
    
    @Override
    public List<FileEntity> getAllActiveFilesInGroup(final Long groupId) {
        return this.fileGroupRepository
                .getAllByGroupId(groupId)
                .stream()
                .map(fileGroupEntity -> this.fileRepository.getAllById(fileGroupEntity.getFileId()))
                .collect(Collectors.toList());
    }
    
    @Override
    public FileEntity saveNewFile(final Long groupId,
                                  final Long userId,
                                  final InputFileDto inputFileDto,
                                  final MultipartFile file) {
        final FileTokenizer fileTokenizer = new FileTokenizer();
        final FileEntity fileEntity = new FileEntity.Builder()
                .fileName(inputFileDto.getFileName())
                .parsedFileContent(TokenizedStringSerializer.serialize(fileTokenizer.tokenize(file,
                        ProgrammingLanguage.valueOf(this.groupRepository.getAllById(groupId).getProgrammingLanguage()))))
                .identifiers(TokenizedStringSerializer.serialize(fileTokenizer.getAllIds()))
                .isActive(true)
                .userId(userId)
                .fileAuthor(inputFileDto.getFileAuthor())
                .build();
        
        final FileEntity savedFileEntity = this.fileRepository.save(fileEntity);
        final GroupEntity groupEntity = this.groupRepository.getAllById(groupId);
        this.fileGroupRepository.save(new FileGroupEntity.Builder()
                .fileId(savedFileEntity.getId())
                .groupId(groupEntity.getId())
                .isFileActiveInGroup(true)
                .build());
        this.storeFile(file, savedFileEntity.getId());
        return savedFileEntity;
    }
    
    private void storeFile(final MultipartFile file, final Long fileId){
        final String fileName = fileId.toString();
        final String absoluteFilePath = this.context.getRealPath("/");
        try {
            final Path uploadedFile = Paths.get(absoluteFilePath + "/" + fileName);
            Files.createFile(uploadedFile);
            Files.copy(file.getInputStream(), uploadedFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (final IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    
    @Override
    public Resource loadFileAsResource(final Long fileId) {
        try {
            final String absoluteFilePath = this.context.getRealPath("/");
            final Path uploadedFile = Paths.get(absoluteFilePath + "/" + fileId.toString());
            final Resource resource = new UrlResource(uploadedFile.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileId.toString());
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileId.toString(), ex);
        }
    }
}
