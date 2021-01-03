package com.abrajner.plagiarismdetector.webapp.controller.gui;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.abrajner.plagiarismdetector.applicationservice.GroupApplicationService;
import com.abrajner.plagiarismdetector.applicationservice.UserAuthenticationApplicationService;
import com.abrajner.plagiarismdetector.gui.dto.GroupDto;
import com.abrajner.plagiarismdetector.gui.dto.UserDto;
import com.abrajner.plagiarismdetector.gui.dto.UserGroupDto;
import com.abrajner.plagiarismdetector.webapp.controller.AbstractGuiController;

@RestController
public class GroupController extends AbstractGuiController {
    
    final GroupApplicationService groupApplicationService;
    
    public GroupController(final UserAuthenticationApplicationService userAuthenticationApplicationService,
                           final GroupApplicationService groupApplicationService) {
        super(userAuthenticationApplicationService);
        this.groupApplicationService = groupApplicationService;
    }
    
    @PostMapping(path = "/groups", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserGroupDto addNewGroup(@RequestBody final GroupDto groupDto,
                                    @RequestHeader("Authentication") final Optional<String> token){
        final UserDto userDto = this.checkAuthenticationToken(token.orElse(""));
        return this.groupApplicationService.validateAndSaveNewGroup(userDto.getId(), groupDto);
    }
    
    @GetMapping(path = "/groups", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserGroupDto> getAllUserGroups(@RequestHeader("Authentication") final Optional<String> token){
        final UserDto userDto = this.checkAuthenticationToken(token.orElse(""));
        return this.groupApplicationService.getAllUsersGroups(userDto.getId());
    }
}
