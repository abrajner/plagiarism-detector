package com.abrajner.plagiarismdetector.gui.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReportsResponse {
    
    private String reportName;
    
    private boolean isFinished;
    
    private List<SingleReport> reportsForFiles = new ArrayList<>();
    
    public String getReportName() {
        return this.reportName;
    }
    
    public void setReportName(final String reportName) {
        this.reportName = reportName;
    }
    
    public boolean isFinished() {
        return this.isFinished;
    }
    
    public void setFinished(final boolean finished) {
        this.isFinished = finished;
    }
    
    public List<SingleReport> getReportsForFiles() {
        return this.reportsForFiles;
    }
    
    public void setReportsForFiles(final List<SingleReport> reportsForFiles) {
        this.reportsForFiles = reportsForFiles;
    }
    
    @Override
    public String toString() {
        return "ReportsResponse{" +
                "reportName='" + this.reportName + '\'' +
                ", isFinished=" + this.isFinished +
                ", reportsForFiles=" + this.reportsForFiles +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final ReportsResponse that = (ReportsResponse) o;
        return this.isFinished == that.isFinished &&
                Objects.equals(this.reportName, that.reportName) &&
                Objects.equals(this.reportsForFiles, that.reportsForFiles);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.reportName, this.isFinished, this.reportsForFiles);
    }
}
