package com.abrajner.plagiarismdetector.applicationservice;

import java.util.List;

import com.abrajner.plagiarismdetector.gui.dto.GroupDto;
import com.abrajner.plagiarismdetector.gui.dto.UserGroupDto;

public interface GroupApplicationService {
    
    UserGroupDto validateAndSaveNewGroup(Long userId, GroupDto groupDto);
    
    List<UserGroupDto> getAllUsersGroups(Long userId);
    
    UserGroupDto updateGroupData(Long groupId, Long userId, GroupDto groupDto);
    
    UserGroupDto getGroupById(Long groupId);
}
