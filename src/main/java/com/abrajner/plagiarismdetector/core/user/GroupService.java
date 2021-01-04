package com.abrajner.plagiarismdetector.core.user;

import java.util.List;

import com.abrajner.plagiarismdetector.dao.entity.GroupEntity;
import com.abrajner.plagiarismdetector.gui.dto.GroupDto;
import com.abrajner.plagiarismdetector.gui.dto.UserGroupDto;

public interface GroupService {
    
    List<GroupEntity> getAllUsersGroups(Long userId);
    
    GroupEntity saveNewGroupInDatabase(UserGroupDto userGroupDto);
    
    void checkIfGroupAlreadyExistsForUser(Long userId, String groupName);
    
    GroupEntity updateGroup(final Long groupId, final UserGroupDto userGroupDto);
}
