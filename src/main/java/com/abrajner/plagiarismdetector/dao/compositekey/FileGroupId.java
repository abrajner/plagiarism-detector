package com.abrajner.plagiarismdetector.dao.compositekey;

import java.io.Serializable;
import java.util.Objects;

public class FileGroupId implements Serializable {
   
    private final Long fileId;
 
    private final Long groupId;
    
    public FileGroupId(final Long fileId, final Long groupId) {
        this.fileId = fileId;
        this.groupId = groupId;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        final FileGroupId that = (FileGroupId) o;
        return Objects.equals(this.fileId, that.fileId) &&
                Objects.equals(this.groupId, that.groupId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.fileId, this.groupId);
    }
}
