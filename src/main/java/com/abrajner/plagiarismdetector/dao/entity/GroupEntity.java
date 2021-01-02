package com.abrajner.plagiarismdetector.dao.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.abrajner.plagiarismdetector.common.Defaults;

@Entity
@Table(name = GroupEntity.TABLE_NAME)
public class GroupEntity {
    
    static final String TABLE_NAME = "groups";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Defaults.CommonEntityColumns.ID)
    private Long id;
    
    @Column(name = Defaults.CommonEntityColumns.USER_ID, nullable = false)
    private Long userId;
    
    @Column(name = Defaults.GroupEntityColumns.GROUP_NAME)
    private String groupName;
    
    public GroupEntity(){
    }
    
    public GroupEntity(final Builder builder){
        this.setId(builder.id);
        this.setUserId(builder.userId);
        this.setGroupName(builder.groupName);
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
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
    
    @Override
    public String toString() {
        return "GroupEntity{" +
                "id=" + this.id +
                ", userId=" + this.userId +
                ", groupName='" + this.groupName + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final GroupEntity that = (GroupEntity) o;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.userId, that.userId) &&
                Objects.equals(this.groupName, that.groupName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.userId, this.groupName);
    }
    
    public static final class Builder{
        private Long id;
        private Long userId;
        private String groupName;
        
        public Builder id(final Long id){
            this.id = id;
            
            return this;
        }
    
        public Builder userId(final Long userId){
            this.userId = userId;
        
            return this;
        }
    
        public Builder groupName(final String groupName){
            this.groupName = groupName;
        
            return this;
        }
        
        public GroupEntity build(){
            return new GroupEntity(this);
        }
    }
}
