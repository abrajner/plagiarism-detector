package com.abrajner.plagiarismdetector.dao.entity;

import java.io.File;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.abrajner.plagiarismdetector.common.Defaults;

@Entity
@Table(name = FileEntity.TABLE_NAME)
public class FileEntity {
    
    static final String TABLE_NAME = "files";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Defaults.CommonEntityColumns.ID)
    private Long id;
    
    @Column(name = Defaults.CommonEntityColumns.USER_ID, nullable = false)
    private Long userId;
    
    @Column(name = Defaults.FileEntityColumns.ATTACHMENT_NAME, nullable = false)
    private String attachmentName;
    
    @Column(name = Defaults.FileEntityColumns.IS_ACTIVE)
    private boolean isActive;
    
    public FileEntity(){
    }
    
    private FileEntity(final Builder builder){
        this.setId(builder.id);
        this.setUserId(builder.userId);
        this.setAttachmentName(builder.attachmentName);
        this.setActive(builder.isActive);
    }
    
    public void setId(final Long userId) {
        this.id = userId;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public Long getUserId() {
        return this.userId;
    }
    
    public void setUserId(final Long userId) {
        this.userId = userId;
    }
    
    public String getAttachmentName() {
        return this.attachmentName;
    }
    
    public void setAttachmentName(final String attachmentName) {
        this.attachmentName = attachmentName;
    }
    
    public boolean isActive() {
        return this.isActive;
    }
    
    public void setActive(final boolean active) {
        this.isActive = active;
    }
    
    @Override
    public String toString() {
        return "FileEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", attachmentName='" + attachmentName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final FileEntity that = (FileEntity) o;
        return this.isActive == that.isActive &&
                Objects.equals(this.id, that.id) &&
                Objects.equals(this.userId, that.userId) &&
                Objects.equals(this.attachmentName, that.attachmentName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.userId, this.attachmentName, this.isActive);
    }
    
    public static final class Builder{
        private Long id;
        private Long userId;
        private String attachmentName;
        private boolean isActive;
        
        public Builder id(final Long id){
            this.id = id;
            return this;
        }
        
        public Builder userId(final Long userId){
            this.userId = userId;
            return this;
        }
        
        public Builder attachmentName(final String attachmentName){
            this.attachmentName = attachmentName;
            return this;
        }
        
        public Builder isActive(final boolean isActive){
            this.isActive = isActive;
            return this;
        }
        
        public FileEntity build(){
            return new FileEntity(this);
        }
    }
}
