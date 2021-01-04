package com.abrajner.plagiarismdetector.applicationservice.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abrajner.plagiarismdetector.applicationservice.GroupApplicationService;
import com.abrajner.plagiarismdetector.core.user.GroupService;
import com.abrajner.plagiarismdetector.dao.repository.GroupRepository;
import com.abrajner.plagiarismdetector.gui.dto.GroupDto;
import com.abrajner.plagiarismdetector.gui.dto.UserGroupDto;
import com.abrajner.plagiarismdetector.mapper.GroupMapper;
import com.abrajner.plagiarismdetector.validator.GroupValidator;
import com.abrajner.plagiarismdetector.validator.Validator;

@Service
public class GroupApplicationServiceImpl implements GroupApplicationService {
    
    final GroupService groupService;
    
    final Validator validator;
    
    final GroupMapper groupMapper;
    
    final GroupRepository groupRepository;
    
    public GroupApplicationServiceImpl(final GroupService groupService,
                                       final GroupMapper groupMapper,
                                       final GroupValidator groupValidator,
                                       final GroupRepository groupRepository) {
        this.groupService = groupService;
        this.validator = groupValidator;
        this.groupMapper = groupMapper;
        this.groupRepository = groupRepository;
    }
    
    @Override
    public UserGroupDto validateAndSaveNewGroup(final Long userId, final GroupDto groupDto) {
        this.validator.validate(groupDto);
        this.groupService.checkIfGroupAlreadyExistsForUser(userId, groupDto.getGroupName());
        return this.groupMapper.convertToDto(this.groupService.saveNewGroupInDatabase(this.creteNewUserGroup(userId, groupDto)));
    }
    
    @Override
    public List<UserGroupDto> getAllUsersGroups(final Long userId) {
        return this.groupService.getAllUsersGroups(userId).stream().map(this.groupMapper::convertToDto).collect(Collectors.toList());
    }
    
    @Override
    public UserGroupDto updateGroupData(final Long groupId, final Long userId, final GroupDto groupDto) {
        this.validator.validate(groupDto);
        return this.groupMapper.convertToDto(this.groupService.updateGroup(groupId, this.creteNewUserGroup(userId, groupDto)));
    }
    
    @Override
    public UserGroupDto getGroupById(final Long groupId) {
        return this.groupMapper.convertToDto(this.groupService.getGroupById(groupId));
    }
    
    private UserGroupDto creteNewUserGroup(final Long userId, final GroupDto groupDto){
        final UserGroupDto userGroupDto = new UserGroupDto();
        userGroupDto.setGroupName(groupDto.getGroupName());
        userGroupDto.setUserId(userId);
        userGroupDto.setProgrammingLanguage(groupDto.getProgrammingLanguage());
        return userGroupDto;
    }
}
