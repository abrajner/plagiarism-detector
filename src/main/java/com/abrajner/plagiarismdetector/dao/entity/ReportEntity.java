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
@Table(name = ReportEntity.TABLE_NAME)
public class ReportEntity {
    
    static final String TABLE_NAME = "report";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Defaults.CommonEntityColumns.ID)
    private Long id;
    
    @Column(name = Defaults.ReportEntityColumns.REPORT_NAME)
    private String reportName;
    
    @Column(name = Defaults.CommonEntityColumns.GROUP_ID)
    private Long groupId;
    
    @Column(name = Defaults.ReportEntityColumns.IS_PLAGIARISM)
    private Boolean isPlagiarism;
    
    @Column(name = Defaults.ReportEntityColumns.SIMILARITY_PERCENTAGE)
    private double codeSimilarityPercentage;
    
    @Column(name = Defaults.ReportEntityColumns.IS_FINISHED)
    private boolean isFinished;
    
    public ReportEntity(){
    }
    
    public ReportEntity(final Builder builder){
        this.setCodeSimilarityPercentage(builder.codeSimilarityPercentage);
        this.setPlagiarism(builder.isPlagiarism);
        this.setFinished(builder.isFinished);
        this.setReportName(builder.reportName);
        this.setGroupId(builder.groupId);
    }
    
    public void setId(final Long userId) {
        this.id = userId;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public Boolean getPlagiarism() {
        return this.isPlagiarism;
    }
    
    public void setPlagiarism(final Boolean plagiarism) {
        this.isPlagiarism = plagiarism;
    }
    
    public double getCodeSimilarityPercentage() {
        return this.codeSimilarityPercentage;
    }
    
    public Long getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(final Long groupId) {
        this.groupId = groupId;
    }
    
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
    
    public void setCodeSimilarityPercentage(final double codeSimilarityPercentage) {
        this.codeSimilarityPercentage = codeSimilarityPercentage;
    }
    
    @Override
    public String toString() {
        return "ReportEntity{" +
                "id=" + this.id +
                ", reportName='" + this.reportName + '\'' +
                ", groupId=" + this.groupId +
                ", isPlagiarism=" + this.isPlagiarism +
                ", codeSimilarityPercentage=" + this.codeSimilarityPercentage +
                ", isFinished=" + this.isFinished +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final ReportEntity that = (ReportEntity) o;
        return Double.compare(that.codeSimilarityPercentage, this.codeSimilarityPercentage) == 0 &&
                this.isFinished == that.isFinished &&
                Objects.equals(this.id, that.id) &&
                Objects.equals(this.reportName, that.reportName) &&
                Objects.equals(this.groupId, that.groupId) &&
                Objects.equals(this.isPlagiarism, that.isPlagiarism);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.reportName, this.groupId, this.isPlagiarism, this.codeSimilarityPercentage, this.isFinished);
    }
    
    public static final class Builder{
        private boolean isPlagiarism;
        private double codeSimilarityPercentage;
        private boolean isFinished;
        private String reportName;
        private Long groupId;
    
        public Builder groupId(final Long groupId){
            this.groupId = groupId;
            return this;
        }
    
        public Builder isPlagiarism(final boolean isPlagiarism){
            this.isPlagiarism = isPlagiarism;
            return this;
        }
    
        public Builder codeSimilarityPercentage(final double codeSimilarityPercentage){
            this.codeSimilarityPercentage = codeSimilarityPercentage;
            return this;
        }
    
        
        public Builder isFinished(final boolean isFinished){
            this.isFinished = isFinished;
            return this;
        }
        
        public Builder reportName(final String reportName){
            this.reportName = reportName;
            return this;
        }
        
        public ReportEntity build(){
            return new ReportEntity(this);
        }
    }
}
