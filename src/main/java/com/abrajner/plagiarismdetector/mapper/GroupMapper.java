package com.abrajner.plagiarismdetector.mapper;

import org.springframework.stereotype.Component;

import com.abrajner.plagiarismdetector.dao.entity.GroupEntity;
import com.abrajner.plagiarismdetector.gui.dto.UserGroupDto;

@Component
public class GroupMapper {
    
    public GroupEntity convertToEntity(final UserGroupDto userGroupDto){
        return new GroupEntity.Builder()
                .groupName(userGroupDto.getGroupName())
                .userId(userGroupDto.getUserId())
                .programmingLanguage(userGroupDto.getProgrammingLanguage())
                .build();
    }
    
    public UserGroupDto convertToDto(final GroupEntity groupEntity){
        final UserGroupDto userGroupDto = new UserGroupDto();
        userGroupDto.setProgrammingLanguage(groupEntity.getProgrammingLanguage());
        userGroupDto.setUserId(groupEntity.getUserId());
        userGroupDto.setGroupId(groupEntity.getId());
        userGroupDto.setGroupName(groupEntity.getGroupName());
        return userGroupDto;
    }
}
