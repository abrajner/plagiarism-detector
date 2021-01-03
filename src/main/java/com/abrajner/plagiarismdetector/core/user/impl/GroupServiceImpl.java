package com.abrajner.plagiarismdetector.core.user.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abrajner.plagiarismdetector.core.user.GroupService;
import com.abrajner.plagiarismdetector.dao.entity.GroupEntity;
import com.abrajner.plagiarismdetector.dao.repository.GroupRepository;
import com.abrajner.plagiarismdetector.gui.dto.UserGroupDto;
import com.abrajner.plagiarismdetector.mapper.GroupMapper;

@Service
public class GroupServiceImpl implements GroupService {
    
    private final GroupRepository groupRepository;
    
    private final GroupMapper groupMapper;
    
    public GroupServiceImpl(final GroupRepository groupRepository,
                            final GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }
    
    @Override
    public List<GroupEntity> getAllUsersGroups(final Long userId) {
        return this.groupRepository.getAllByUserId(userId);
    }
    
    @Override
    public GroupEntity saveNewGroupInDatabase(final UserGroupDto userGroupDto) {
        return this.groupRepository.save(this.groupMapper.convertToEntity(userGroupDto));
    }
    
    @Override
    public void checkIfGroupAlreadyExistsForUser(final Long userId, final String groupName) {
        if(!this.groupRepository.getAllByGroupNameAndUserId(groupName, userId).isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Group with this name already exists");
        }
    }
}
