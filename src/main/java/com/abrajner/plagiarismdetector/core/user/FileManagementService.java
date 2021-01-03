package com.abrajner.plagiarismdetector.core.user;

import java.util.List;

import com.abrajner.plagiarismdetector.dao.entity.FileEntity;
import com.abrajner.plagiarismdetector.gui.dto.InputFileDto;

public interface FileManagementService {
    
    List<FileEntity> getAllActiveFilesInGroup(Long groupId);
    
    FileEntity saveNewFile(Long groupId, InputFileDto inputFileDto);
}
