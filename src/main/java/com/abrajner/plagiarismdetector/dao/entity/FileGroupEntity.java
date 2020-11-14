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
@Table(name = FileGroupEntity.TABLE_NAME)
public class FileGroupEntity {
    
    static final String TABLE_NAME = "fileGroups";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Defaults.CommonEntityColumns.ID)
    private Long id;
    
    @Column(name = Defaults.CommonEntityColumns.FILE_ID, nullable = false)
    private Long fileId;
    
    @Column(name = Defaults.CommonEntityColumns.GROUP_ID, nullable = false)
    private Long groupId;
    
    @Column(name = Defaults.FileGroupEntityColumns.IS_FILE_ACTIVE_IN_GROUP)
    private boolean isFileActiveInGroup;
    
    public FileGroupEntity(){
    }
    
    private FileGroupEntity(final Builder builder){
        this.setId(builder.id);
        this.setFileId(builder.fileId);
        this.setGroupId(builder.groupId);
        this.setFileActiveInGroup(builder.isFileActiveInGroup);
    }
    
    public void setId(final Long userId) {
        this.id = userId;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public Long getFileId() {
        return this.fileId;
    }
    
    public void setFileId(final Long fileId) {
        this.fileId = fileId;
    }
    
    public Long getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(final Long groupId) {
        this.groupId = groupId;
    }
    
    public boolean isFileActiveInGroup() {
        return this.isFileActiveInGroup;
    }
    
    public void setFileActiveInGroup(final boolean fileActiveInGroup) {
        this.isFileActiveInGroup = fileActiveInGroup;
    }
    
    @Override
    public String toString() {
        return "FileGroupEntity{" +
                "id=" + id +
                ", fileId=" + fileId +
                ", groupId=" + groupId +
                ", isFileActiveInGroup=" + isFileActiveInGroup +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final FileGroupEntity that = (FileGroupEntity) o;
        return this.isFileActiveInGroup == that.isFileActiveInGroup &&
                Objects.equals(this.id, that.id) &&
                Objects.equals(this.fileId, that.fileId) &&
                Objects.equals(this.groupId, that.groupId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.fileId, this.groupId, this.isFileActiveInGroup);
    }
    
    public static final class Builder{
        private Long id;
        private Long fileId;
        private Long groupId;
        private boolean isFileActiveInGroup;
        
        public Builder id(final Long id){
            this.id = id;
            
            return this;
        }
        
        public Builder fileId(final Long fileId){
            this.fileId = fileId;
            
            return this;
        }
        
        public Builder groupId(final Long groupId){
            this.groupId = groupId;
            
            return this;
        }
        
        public Builder isFileActiveInGroup(final boolean isFileActiveInGroup){
            this.isFileActiveInGroup = isFileActiveInGroup;
            
            return this;
        }
        
        public FileGroupEntity build(){
            return new FileGroupEntity(this);
        }
    }
}
