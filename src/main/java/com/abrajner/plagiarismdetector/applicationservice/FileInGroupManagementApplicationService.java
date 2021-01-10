package com.abrajner.plagiarismdetector.applicationservice;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.abrajner.plagiarismdetector.gui.dto.FileReducedDto;
import com.abrajner.plagiarismdetector.gui.dto.InputFileDto;

public interface FileInGroupManagementApplicationService {
    
    List<FileReducedDto> getAllFilesFromGroup(Long groupId);
    
    FileReducedDto validateAndSaveNewFile(Long groupId, Long userId, InputFileDto fileDto, MultipartFile file);
    
    Resource downloadFileContent(Long fileId);
}
