package com.abrajner.plagiarismdetector.fileparser;

import java.util.List;

public class ParseFileContent {
    
    public ParsedFile parse(final ParsedFile parsedFile, final ProgrammingLanguage programmingLanguage){
        programmingLanguage.parse(parsedFile);
        return parsedFile;
    }
}
