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
    
    static final String TABLE_NAME = "reports";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Defaults.CommonEntityColumns.ID)
    private Long id;
    
    @Column(name = Defaults.ReportEntityColumns.IS_PLAGIARISM)
    private Boolean isPlagiarism;
    
    @Column(name = Defaults.ReportEntityColumns.SIMILARITY_PERCENTAGE)
    private Integer codeSimilarityPercentage;
    
    public ReportEntity(){
    }
    
    public ReportEntity(final Builder builder){
        this.setId(builder.id);
        this.setCodeSimilarityPercentage(builder.codeSimilarityPercentage);
        this.setPlagiarism(builder.isPlagiarism);
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
    
    public Integer getCodeSimilarityPercentage() {
        return this.codeSimilarityPercentage;
    }
    
    public void setCodeSimilarityPercentage(final Integer codeSimilarityPercentage) {
        this.codeSimilarityPercentage = codeSimilarityPercentage;
    }
    
    @Override
    public String toString() {
        return "ReportEntity{" +
                "id=" + this.id +
                ", isPlagiarism=" + this.isPlagiarism +
                ", codeSimilarityPercentage=" + this.codeSimilarityPercentage +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final ReportEntity that = (ReportEntity) o;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.isPlagiarism, that.isPlagiarism) &&
                Objects.equals(this.codeSimilarityPercentage, that.codeSimilarityPercentage);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.isPlagiarism, this.codeSimilarityPercentage);
    }
    
    public static final class Builder{
        private Long id;
        private boolean isPlagiarism;
        private Integer codeSimilarityPercentage;
    
        public Builder id(final Long id){
            this.id = id;
            return this;
        }
    
        public Builder isPlagiarism(final boolean isPlagiarism){
            this.isPlagiarism = isPlagiarism;
            return this;
        }
    
        public Builder codeSimilarityPercentage(final Integer codeSimilarityPercentage){
            this.codeSimilarityPercentage = codeSimilarityPercentage;
            return this;
        }
        
        public ReportEntity build(){
            return new ReportEntity(this);
        }
    }
}
