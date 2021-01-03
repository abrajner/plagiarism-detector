package com.abrajner.plagiarismdetector.mapper;

import org.springframework.stereotype.Component;

import com.abrajner.plagiarismdetector.dao.entity.FileEntity;
import com.abrajner.plagiarismdetector.gui.dto.FileReducedDto;
import com.abrajner.plagiarismdetector.gui.dto.InputFileDto;

@Component
public class FileMapper {
    
    public FileReducedDto convertToReducedDto(final FileEntity fileEntity){
        final FileReducedDto fileReducedDto = new FileReducedDto();
        fileReducedDto.setAttachmentId(fileEntity.getId());
        fileReducedDto.setFileAuthor(fileEntity.getFileAuthor());
        fileReducedDto.setFileName(fileEntity.getFileName());
        return fileReducedDto;
    }
    
    public FileEntity convertToEntity(final InputFileDto inputFileDto){
        return new FileEntity.Builder().isActive(true)
                .fileAuthor(inputFileDto.getFileAuthor())
                .fileName(inputFileDto.getFileName())
                .build();
    }
}
