package com.abrajner.plagiarismdetector.dao.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.abrajner.plagiarismdetector.common.Defaults;
import com.abrajner.plagiarismdetector.dao.compositekey.FileGroupId;
import com.abrajner.plagiarismdetector.dao.compositekey.FileReportId;

@Entity
@Table(name = FileReportEntity.TABLE_NAME)
@IdClass(FileReportId.class)
public class FileReportEntity {
    
    static final String TABLE_NAME = "fileReport";
    
    @Id
    @Column(name = Defaults.CommonEntityColumns.REPORT_ID, nullable = false)
    private Long reportId;
    
    @Id
    @Column(name = Defaults.CommonEntityColumns.FILE_ID, nullable = false)
    private Long fileId;
    
    public FileReportEntity(){
    }
    
    public FileReportEntity(final Builder builder){
        this.setFileId(builder.fileId);
        this.setReportId(builder.reportId);
    }
    
    public Long getReportId() {
        return this.reportId;
    }
    
    public void setReportId(final Long reportId) {
        this.reportId = reportId;
    }
    
    public Long getFileId() {
        return this.fileId;
    }
    
    public void setFileId(final Long fileId) {
        this.fileId = fileId;
    }
    
    @Override
    public String toString() {
        return "FileReportEntity{" +
                "reportId=" + this.reportId +
                ", fileId=" + this.fileId +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final FileReportEntity that = (FileReportEntity) o;
        return Objects.equals(this.reportId, that.reportId) &&
                Objects.equals(this.fileId, that.fileId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.reportId, this.fileId);
    }
    
    public static final class Builder{
        private Long reportId;
        private Long fileId;
        
        public Builder reportId(final Long reportId){
            this.reportId = reportId;
            
            return this;
        }
        
        public Builder fileId(final Long fileId){
            this.fileId = fileId;
            
            return this;
        }
        
        public FileReportEntity build(){
            return new FileReportEntity(this);
        }
    }
}
