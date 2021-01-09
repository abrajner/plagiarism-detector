package com.abrajner.plagiarismdetector.core.user;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.abrajner.plagiarismdetector.dao.entity.FileEntity;
import com.abrajner.plagiarismdetector.gui.dto.InputFileDto;

public interface FileManagementService {
    
    List<FileEntity> getAllActiveFilesInGroup(Long groupId);
    
    FileEntity saveNewFile(Long groupId, Long userId, InputFileDto inputFileDto, MultipartFile file);
}
