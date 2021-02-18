package com.abrajner.plagiarismdetector.gui.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SingleReport {
    
    private Long firstFileId;
    
    private Long secondFileId;
    
    private String firstFileName;
    
    private String secondFileName;
    
    private String firstFileAuthor;
    
    private String secondFileAuthor;
    
    private double codeSimilarityPercentage;
    
    private boolean isPlagiarism;
    
    public Long getFirstFileId() {
        return this.firstFileId;
    }
    
    public void setFirstFileId(final Long firstFileId) {
        this.firstFileId = firstFileId;
    }
    
    public Long getSecondFileId() {
        return this.secondFileId;
    }
    
    public void setSecondFileId(final Long secondFileId) {
        this.secondFileId = secondFileId;
    }
    
    public String getFirstFileName() {
        return this.firstFileName;
    }
    
    public void setFirstFileName(final String firstFileName) {
        this.firstFileName = firstFileName;
    }
    
    public String getSecondFileName() {
        return this.secondFileName;
    }
    
    public void setSecondFileName(final String secondFileName) {
        this.secondFileName = secondFileName;
    }
    
    public String getFirstFileAuthor() {
        return this.firstFileAuthor;
    }
    
    public void setFirstFileAuthor(final String firstFileAuthor) {
        this.firstFileAuthor = firstFileAuthor;
    }
    
    public String getSecondFileAuthor() {
        return this.secondFileAuthor;
    }
    
    public void setSecondFileAuthor(final String secondFileAuthor) {
        this.secondFileAuthor = secondFileAuthor;
    }
    
    public double getCodeSimilarityPercentage() {
        return this.codeSimilarityPercentage;
    }
    
    public void setCodeSimilarityPercentage(final double codeSimilarityPercentage) {
        this.codeSimilarityPercentage = codeSimilarityPercentage;
    }
    
    public boolean isPlagiarism() {
        return this.isPlagiarism;
    }
    
    public void setPlagiarism(final boolean plagiarism) {
        this.isPlagiarism = plagiarism;
    }
    
    @Override
    public String toString() {
        return "SingleReport{" +
                "firstFileId=" + this.firstFileId +
                ", secondFileId=" + this.secondFileId +
                ", firstFileName='" + this.firstFileName + '\'' +
                ", secondFileName='" + this.secondFileName + '\'' +
                ", firstFileAuthor='" + this.firstFileAuthor + '\'' +
                ", secondFileAuthor='" + this.secondFileAuthor + '\'' +
                ", codeSimilarityPercentage=" + this.codeSimilarityPercentage +
                ", isPlagiarism=" + this.isPlagiarism +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final SingleReport that = (SingleReport) o;
        return Double.compare(that.codeSimilarityPercentage, this.codeSimilarityPercentage) == 0 &&
                this.isPlagiarism == that.isPlagiarism &&
                Objects.equals(this.firstFileId, that.firstFileId) &&
                Objects.equals(this.secondFileId, that.secondFileId) &&
                Objects.equals(this.firstFileName, that.firstFileName) &&
                Objects.equals(this.secondFileName, that.secondFileName) &&
                Objects.equals(this.firstFileAuthor, that.firstFileAuthor) &&
                Objects.equals(this.secondFileAuthor, that.secondFileAuthor);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.firstFileId, this.secondFileId, this.firstFileName, this.secondFileName, this.firstFileAuthor, this.secondFileAuthor, this.codeSimilarityPercentage, this.isPlagiarism);
    }
}
