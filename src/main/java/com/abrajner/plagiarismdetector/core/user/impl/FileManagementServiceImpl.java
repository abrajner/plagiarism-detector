package com.abrajner.plagiarismdetector.core.user.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abrajner.plagiarismdetector.core.user.FileManagementService;
import com.abrajner.plagiarismdetector.dao.entity.FileEntity;
import com.abrajner.plagiarismdetector.dao.entity.FileGroupEntity;
import com.abrajner.plagiarismdetector.dao.entity.GroupEntity;
import com.abrajner.plagiarismdetector.dao.repository.FileGroupRepository;
import com.abrajner.plagiarismdetector.dao.repository.FileRepository;
import com.abrajner.plagiarismdetector.dao.repository.GroupRepository;
import com.abrajner.plagiarismdetector.gui.dto.InputFileDto;
import com.abrajner.plagiarismdetector.mapper.FileMapper;

@Service
public class FileManagementServiceImpl implements FileManagementService {
    
    final FileGroupRepository fileGroupRepository;
    
    final GroupRepository groupRepository;
    
    final FileRepository fileRepository;
    
    final FileMapper fileMapper;
    
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
    public FileEntity saveNewFile(final Long groupId, final InputFileDto inputFileDto) {
        final FileEntity fileEntity = this.fileRepository.save(this.fileMapper.convertToEntity(inputFileDto));
        final GroupEntity groupEntity = this.groupRepository.getAllById(groupId);
        this.fileGroupRepository.save(new FileGroupEntity.Builder().fileId(fileEntity.getId()).groupId(groupEntity.getId()).isFileActiveInGroup(true).build());
        return fileEntity;
    }
}
