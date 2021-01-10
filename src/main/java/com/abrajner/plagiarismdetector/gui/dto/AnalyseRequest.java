package com.abrajner.plagiarismdetector.gui.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnalyseRequest {
    
    private List<Long> fileIds = new ArrayList<>();
    
    private String reportName;
    
    public List<Long> getFileIds() {
        return this.fileIds;
    }
    
    public void setFileIds(final List<Long> fileIds) {
        this.fileIds = fileIds;
    }
    
    public String getReportName() {
        return this.reportName;
    }
    
    public void setReportName(final String reportName) {
        this.reportName = reportName;
    }
    
    @Override
    public String toString() {
        return "AnalyseRequest{" +
                "fileIds=" + this.fileIds +
                ", reportName='" + this.reportName + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final AnalyseRequest that = (AnalyseRequest) o;
        return Objects.equals(this.fileIds, that.fileIds) &&
                Objects.equals(this.reportName, that.reportName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.fileIds, this.reportName);
    }
}
