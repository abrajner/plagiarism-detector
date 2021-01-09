package com.abrajner.plagiarismdetector.dao.compositekey;

import java.io.Serializable;
import java.util.Objects;

public class FileReportId implements Serializable {
    
    private Long reportId;
    
    private Long fileId;
    
    public FileReportId(){
    }
    
    public FileReportId(final Long reportId, final Long fileId) {
        this.reportId = reportId;
        this.fileId = fileId;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final FileReportId that = (FileReportId) o;
        return Objects.equals(this.reportId, that.reportId) &&
                Objects.equals(this.fileId, that.fileId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.reportId, this.fileId);
    }
}
