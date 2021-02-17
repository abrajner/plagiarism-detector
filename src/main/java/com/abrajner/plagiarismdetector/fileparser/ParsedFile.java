package com.abrajner.plagiarismdetector.fileparser;

import java.util.ArrayList;
import java.util.List;

public class ParsedFile {
    
    private final List<String> fileContent = new ArrayList<>();
    
    private final List<List<String>> fileContentByFunctions = new ArrayList<>();
    
    private final List<List<String>> fileContentByInstructions = new ArrayList<>();
    
    private final List<String> identifiers = new ArrayList<>();
    
    private final List<String> identifiersByEquals = new ArrayList<>();
    
    public List<String> getFileContent() {
        return this.fileContent;
    }
    
    public List<List<String>> getFileContentByFunctions() {
        return this.fileContentByFunctions;
    }
    
    public List<List<String>> getFileContentByInstructions() {
        return this.fileContentByInstructions;
    }
    
    public List<String> getIdentifiers() {
        return this.identifiers;
    }
    
    public List<String> getIdentifiersByEquals() {
        return this.identifiersByEquals;
    }
}
