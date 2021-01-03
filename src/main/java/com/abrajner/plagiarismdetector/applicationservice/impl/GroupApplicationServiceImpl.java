package com.abrajner.plagiarismdetector.applicationservice.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.abrajner.plagiarismdetector.applicationservice.GroupApplicationService;
import com.abrajner.plagiarismdetector.core.user.GroupService;
import com.abrajner.plagiarismdetector.gui.dto.GroupDto;
import com.abrajner.plagiarismdetector.gui.dto.UserGroupDto;
import com.abrajner.plagiarismdetector.mapper.GroupMapper;
import com.abrajner.plagiarismdetector.validator.AbstractValidator;

@Service
public class GroupApplicationServiceImpl implements GroupApplicationService {
    
    final GroupService groupService;
    
    final AbstractValidator validator;
    
    final GroupMapper groupMapper;
    
    public GroupApplicationServiceImpl(final GroupService groupService,
                                       @Qualifier("abstractValidator") final AbstractValidator validator,
                                       final GroupMapper groupMapper) {
        this.groupService = groupService;
        this.validator = validator;
        this.groupMapper = groupMapper;
    }
    
    @Override
    public UserGroupDto validateAndSaveNewGroup(final Long userId, final GroupDto groupDto) {
        
        this.groupService.checkIfGroupAlreadyExistsForUser(userId, groupDto.getGroupName());
        return this.groupMapper.convertToDto(this.groupService.saveNewGroupInDatabase(this.creteNewUserGroup(userId, groupDto)));
    }
    
    @Override
    public List<UserGroupDto> getAllUsersGroups(final Long userId) {
        return this.groupService.getAllUsersGroups(userId).stream().map(this.groupMapper::convertToDto).collect(Collectors.toList());
    }
    
    private UserGroupDto creteNewUserGroup(final Long userId, final GroupDto groupDto){
        final UserGroupDto userGroupDto = new UserGroupDto();
        userGroupDto.setGroupName(groupDto.getGroupName());
        userGroupDto.setUserId(userId);
        userGroupDto.setProgrammingLanguage(groupDto.getProgrammingLanguage());
        return userGroupDto;
    }
}
