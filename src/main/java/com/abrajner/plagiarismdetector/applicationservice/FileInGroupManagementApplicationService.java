package com.abrajner.plagiarismdetector.applicationservice;

import java.util.List;

import com.abrajner.plagiarismdetector.gui.dto.FileReducedDto;
import com.abrajner.plagiarismdetector.gui.dto.InputFileDto;

public interface FileInGroupManagementApplicationService {
    
    List<FileReducedDto> getAllFilesFromGroup(Long groupId);
    
    FileReducedDto validateAndSaveNewFile(Long groupId, InputFileDto fileDto);
}
