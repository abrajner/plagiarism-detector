package com.abrajner.plagiarismdetector.restapi.controller.gui;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abrajner.plagiarismdetector.applicationservice.FileInGroupManagementApplicationService;
import com.abrajner.plagiarismdetector.applicationservice.UserAuthenticationApplicationService;
import com.abrajner.plagiarismdetector.core.user.StorageService;
import com.abrajner.plagiarismdetector.gui.dto.FileReducedDto;
import com.abrajner.plagiarismdetector.gui.dto.InputFileDto;
import com.abrajner.plagiarismdetector.gui.dto.UserDto;
import com.abrajner.plagiarismdetector.restapi.controller.AbstractGuiController;

@RestController
public class FileInGroupController extends AbstractGuiController {
    
    private final FileInGroupManagementApplicationService fileInGroupManagementApplicationService;
    
    public FileInGroupController(final UserAuthenticationApplicationService userAuthenticationApplicationService,
                                 final FileInGroupManagementApplicationService fileInGroupManagementApplicationService) {
        super(userAuthenticationApplicationService);
        this.fileInGroupManagementApplicationService = fileInGroupManagementApplicationService;
    }
    
    @GetMapping(path = "/group/{group_id}/files", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<FileReducedDto> getAllFilesIsGroup(@PathVariable("group_id") final String groupId,
                                                   @RequestHeader("Authorization") final Optional<String> token){
        this.checkAuthenticationToken(token.orElse(""));
        return this.fileInGroupManagementApplicationService.getAllFilesFromGroup(Long.valueOf(groupId));
    }
    
    @GetMapping(path = "/file/{file_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Resource> getFileContent(@PathVariable("file_id") final String fileId,
                                                   @RequestHeader("Authorization") final Optional<String> token,
                                                   final HttpServletRequest request){
        this.checkAuthenticationToken(token.orElse(""));
        final Resource resource = this.fileInGroupManagementApplicationService.downloadFileContent(Long.valueOf(fileId));
        
        String contentType = null;
        
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (final IOException ignored) {
        }
        
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    
    @PostMapping(path = "/group/{group_id}/files",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public FileReducedDto addNewFileToGroup(@PathVariable("group_id") final String groupId,
                                            @RequestHeader("Authorization") final Optional<String> token,
                                            @RequestPart("file") final MultipartFile file,
                                            @RequestParam(name = "author") final String author,
                                            @RequestParam(name = "fileName") final String fileName){
    
        UserDto userDto = this.checkAuthenticationToken(token.orElse(""));
        InputFileDto inputFileDto = new InputFileDto();
        inputFileDto.setFileAuthor(author);
        inputFileDto.setFileName(fileName.isEmpty() ? file.getName() : fileName);
        return this.fileInGroupManagementApplicationService.validateAndSaveNewFile(Long.valueOf(groupId), userDto.getId(), inputFileDto, file);
    }
}
