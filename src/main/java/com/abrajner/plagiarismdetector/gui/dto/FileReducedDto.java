package com.abrajner.plagiarismdetector.gui.dto;

import java.util.Objects;

public class FileReducedDto {
    
    private Long attachmentId;
    
    private String fileName;
    
    private String fileAuthor;
    
    public Long getAttachmentId() {
        return this.attachmentId;
    }
    
    public void setAttachmentId(final Long attachmentId) {
        this.attachmentId = attachmentId;
    }
    
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
        return "FileDto{" +
                "attachmentId=" + this.attachmentId +
                ", fileName='" + this.fileName + '\'' +
                ", fileAuthor='" + this.fileAuthor + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final FileReducedDto fileReducedDto = (FileReducedDto) o;
        return Objects.equals(this.attachmentId, fileReducedDto.attachmentId) &&
                Objects.equals(this.fileName, fileReducedDto.fileName) &&
                Objects.equals(this.fileAuthor, fileReducedDto.fileAuthor);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.attachmentId, this.fileName, this.fileAuthor);
    }
}
