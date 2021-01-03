package com.abrajner.plagiarismdetector.gui.dto;

import java.util.Objects;

public class UserGroupDto {
    
    private Long groupId;
    
    private Long userId;
    
    private String groupName;
    
    private String programmingLanguage;
    
    public Long getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(final Long groupId) {
        this.groupId = groupId;
    }
    
    public Long getUserId() {
        return this.userId;
    }
    
    public void setUserId(final Long userId) {
        this.userId = userId;
    }
    
    public String getGroupName() {
        return this.groupName;
    }
    
    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }
    
    public String getProgrammingLanguage() {
        return this.programmingLanguage;
    }
    
    public void setProgrammingLanguage(final String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
    
    @Override
    public String toString() {
        return "UserGroupDto{" +
                "groupId=" + this.groupId +
                ", userId=" + this.userId +
                ", groupName='" + this.groupName + '\'' +
                ", programmingLanguage='" + this.programmingLanguage + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final UserGroupDto that = (UserGroupDto) o;
        return Objects.equals(this.groupId, that.groupId) &&
                Objects.equals(this.userId, that.userId) &&
                Objects.equals(this.groupName, that.groupName) &&
                Objects.equals(this.programmingLanguage, that.programmingLanguage);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.groupId, this.userId, this.groupName, this.programmingLanguage);
    }
}
