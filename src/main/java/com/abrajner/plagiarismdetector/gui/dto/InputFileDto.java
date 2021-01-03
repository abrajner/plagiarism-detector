package com.abrajner.plagiarismdetector.gui.dto;

import java.util.Objects;

public class InputFileDto {
    
    private String fileName;
    
    private String fileAuthor;
    
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public String getFileAuthor() {
        return this.fileAuthor;
    }
    
    public void setFileAuthor(final String fileAuthor) {
        this.fileAuthor = fileAuthor;
    }
    
    @Override
    public String toString() {
        return "InputFileDto{" +
                "fileName='" + this.fileName + '\'' +
                ", fileAuthor='" + this.fileAuthor + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final InputFileDto that = (InputFileDto) o;
        return Objects.equals(this.fileName, that.fileName) &&
                Objects.equals(this.fileAuthor, that.fileAuthor);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.fileName, this.fileAuthor);
    }
}
