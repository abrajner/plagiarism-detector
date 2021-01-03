package com.abrajner.plagiarismdetector.webapp.controller.gui;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.abrajner.plagiarismdetector.applicationservice.FileInGroupManagementApplicationService;
import com.abrajner.plagiarismdetector.applicationservice.UserAuthenticationApplicationService;
import com.abrajner.plagiarismdetector.core.user.StorageService;
import com.abrajner.plagiarismdetector.gui.dto.FileReducedDto;
import com.abrajner.plagiarismdetector.gui.dto.InputFileDto;
import com.abrajner.plagiarismdetector.webapp.controller.AbstractGuiController;

@RestController
public class FileInGroupController extends AbstractGuiController {
    
    private final StorageService storageService;
    
    private final FileInGroupManagementApplicationService fileInGroupManagementApplicationService;
    
    public FileInGroupController(final UserAuthenticationApplicationService userAuthenticationApplicationService,
                                 final StorageService storageService,
                                 final FileInGroupManagementApplicationService fileInGroupManagementApplicationService) {
        super(userAuthenticationApplicationService);
        this.storageService = storageService;
        this.fileInGroupManagementApplicationService = fileInGroupManagementApplicationService;
    }
    
    @GetMapping(path = "/group/{group_id}/files", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<FileReducedDto> getAllFilesIsGroup(@PathVariable("group_id") final String groupId,
                                                   @RequestHeader("Authorization") final Optional<String> token){
        this.checkAuthenticationToken(token.orElse(""));
        return this.fileInGroupManagementApplicationService.getAllFilesFromGroup(Long.valueOf(groupId));
    }
    
    @PostMapping(path = "/group/{group_id}/files", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public FileReducedDto addNewFileToGroup(@PathVariable("group_id") final String groupId,
                                            @RequestHeader("Authorization") final Optional<String> token,
                                            @RequestBody final InputFileDto inputFileDto){
        
        this.checkAuthenticationToken(token.orElse(""));
        return this.fileInGroupManagementApplicationService.validateAndSaveNewFile(Long.valueOf(groupId), inputFileDto);
    }
}