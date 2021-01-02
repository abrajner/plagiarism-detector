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
@Table(name = FileEntity.TABLE_NAME)
public class FileEntity {
    
    static final String TABLE_NAME = "file";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Defaults.CommonEntityColumns.ID)
    private Long id;
    
    @Column(name = Defaults.CommonEntityColumns.USER_ID, nullable = false)
    private Long userId;
    
    @Column(name = Defaults.FileEntityColumns.FILE_NAME, nullable = false)
    private String fileName;
    
    @Column(name = Defaults.FileEntityColumns.FILE_AUTHOR)
    private String fileAuthor;
    
    @Column(name = Defaults.FileEntityColumns.FILE_CONTENT, nullable = false)
    private String fileContent;
    
    @Column(name = Defaults.FileEntityColumns.PARSED_FILE_CONTENT, nullable = false)
    private String parsedFileContent;
    
    @Column(name = Defaults.FileEntityColumns.IS_ACTIVE)
    private boolean isActive;
    
    public FileEntity(){
    }
    
    private FileEntity(final Builder builder){
        this.setId(builder.id);
        this.setUserId(builder.userId);
        this.setFileName(builder.fileName);
        this.setFileContent(builder.fileContent);
        this.setParsedFileContent(builder.parsedFileContent);
        this.setFileAuthor(builder.fileAuthor);
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
    
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(final String fileName){
        this.fileName = fileName;
    }
    
    public String getFileAuthor() {
        return this.fileAuthor;
    }
    
    public void setFileAuthor(final String fileAuthor) {
        this.fileAuthor = fileAuthor;
    }
    
    public String getFileContent() {
        return this.fileContent;
    }
    
    public void setFileContent(final String fileContent) {
        this.fileContent = fileContent;
    }
    
    public String getParsedFileContent() {
        return this.parsedFileContent;
    }
    
    public void setParsedFileContent(final String parsedFileContent) {
        this.parsedFileContent = parsedFileContent;
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
                "id=" + this.id +
                ", userId=" + this.userId +
                ", fileName='" + this.fileName +
                ", fileAuthor='" + this.fileAuthor +
                ", fileContent='" + this.fileContent +
                ", parsedFileContent='" + this.parsedFileContent +
                ", isActive=" + this.isActive +
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
                Objects.equals(this.fileName, that.fileName) &&
                Objects.equals(this.fileAuthor, that.fileAuthor) &&
                Objects.equals(this.fileContent, that.fileContent) &&
                Objects.equals(this.parsedFileContent, that.parsedFileContent);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.userId, this.fileName, this.fileAuthor, this.fileContent, this.parsedFileContent, this.isActive);
    }
    
    public static final class Builder{
        private Long id;
        private Long userId;
        private String fileName;
        private String fileAuthor;
        private String fileContent;
        private String parsedFileContent;
        private boolean isActive;
        
        public Builder id(final Long id){
            this.id = id;
            return this;
        }
        
        public Builder userId(final Long userId){
            this.userId = userId;
            return this;
        }
        
        public Builder fileName(final String fileName){
            this.fileName = fileName;
            return this;
        }
    
        public Builder fileAuthor(final String fileAuthor){
            this.fileAuthor = fileAuthor;
            return this;
        }
    
        public Builder fileContent(final String fileContent){
            this.fileContent = fileContent;
            return this;
        }
    
        public Builder parsedFileContent(final String parsedFileContent){
            this.parsedFileContent = parsedFileContent;
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
