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
    
    @Column(name = Defaults.ReportEntityColumns.IS_PLAGIARISM)
    private Boolean isPlagiarism;
    
    @Column(name = Defaults.ReportEntityColumns.SIMILARITY_PERCENTAGE)
    private double codeSimilarityPercentage;
    
    @Column(name = Defaults.ReportEntityColumns.SIMILARITY_PERCENTAGE_WITH_SUBSTITUTION)
    private double codeSimilarityPercentageWithSubstitution;
    
    public ReportEntity(){
    }
    
    public ReportEntity(final Builder builder){
        this.setId(builder.id);
        this.setCodeSimilarityPercentage(builder.codeSimilarityPercentage);
        this.setPlagiarism(builder.isPlagiarism);
        this.setCodeSimilarityPercentageWithSubstitution(builder.codeSimilarityPercentageWithSubstitution);
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
    
    public double getCodeSimilarityPercentageWithSubstitution() {
        return this.codeSimilarityPercentageWithSubstitution;
    }
    
    public void setCodeSimilarityPercentageWithSubstitution(final double codeSimilarityPercentageWithSubstitution) {
        this.codeSimilarityPercentageWithSubstitution = codeSimilarityPercentageWithSubstitution;
    }
    
    public void setCodeSimilarityPercentage(final double codeSimilarityPercentage) {
        this.codeSimilarityPercentage = codeSimilarityPercentage;
    }
    
    @Override
    public String toString() {
        return "ReportEntity{" +
                "id=" + id +
                ", isPlagiarism=" + isPlagiarism +
                ", codeSimilarityPercentage=" + codeSimilarityPercentage +
                ", codeSimilarityPercentageWithSubstitution=" + codeSimilarityPercentageWithSubstitution +
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
                Objects.equals(this.codeSimilarityPercentage, that.codeSimilarityPercentage) &&
                Objects.equals(this.codeSimilarityPercentageWithSubstitution, that.codeSimilarityPercentageWithSubstitution);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.isPlagiarism, this.codeSimilarityPercentage, this.codeSimilarityPercentageWithSubstitution);
    }
    
    public static final class Builder{
        private Long id;
        private boolean isPlagiarism;
        private double codeSimilarityPercentage;
        private double codeSimilarityPercentageWithSubstitution;
    
        public Builder id(final Long id){
            this.id = id;
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
    
        public Builder codeSimilarityPercentageWithSubstitution(final double codeSimilarityPercentageWithSubstitution){
            this.codeSimilarityPercentageWithSubstitution = codeSimilarityPercentageWithSubstitution;
            return this;
        }
        
        public ReportEntity build(){
            return new ReportEntity(this);
        }
    }
}
