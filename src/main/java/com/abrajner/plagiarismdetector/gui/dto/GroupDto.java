package com.abrajner.plagiarismdetector.gui.dto;

import java.util.Objects;

public class GroupDto {
    
    private String groupName;
    
    private String programmingLanguage;
    
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
        return "GroupDto{" +
                "groupName='" + this.groupName + '\'' +
                ", programmingLanguage='" + this.programmingLanguage + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final GroupDto groupDto = (GroupDto) o;
        return Objects.equals(this.groupName, groupDto.groupName) &&
                Objects.equals(this.programmingLanguage, groupDto.programmingLanguage);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.groupName, this.programmingLanguage);
    }
}
