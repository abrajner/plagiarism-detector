package com.abrajner.plagiarismdetector.gui.dto;

import java.util.Objects;

public class AnalyseResponse {
    
    private String reportName;
    
    public String getReportName() {
        return this.reportName;
    }
    
    public void setReportName(final String reportName) {
        this.reportName = reportName;
    }
    
    @Override
    public String toString() {
        return "AnalyseResponse{" +
                "reportName='" + reportName + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final AnalyseResponse that = (AnalyseResponse) o;
        return Objects.equals(this.reportName, that.reportName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.reportName);
    }
}
