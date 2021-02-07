package com.abrajner.plagiarismdetector.fileparser;

import com.abrajner.plagiarismdetector.fileparser.languageparser.CPPParser;
import com.abrajner.plagiarismdetector.fileparser.languageparser.CParser;
import com.abrajner.plagiarismdetector.fileparser.languageparser.JavaParser;
import com.abrajner.plagiarismdetector.fileparser.languageparser.JavaScriptParser;
import com.abrajner.plagiarismdetector.fileparser.languageparser.LanguageParser;
import com.abrajner.plagiarismdetector.fileparser.languageparser.OtherParser;
import com.abrajner.plagiarismdetector.fileparser.languageparser.PythonParser;

public enum ProgrammingLanguage implements LanguageParser {
    PYTHON(parsedFile -> {
        PythonParser parser = new PythonParser();
        parser.parse(parsedFile);
    }),
    JAVA(parsedFile -> {
        JavaParser parser = new JavaParser();
        parser.parse(parsedFile);
    }),
    CPP(parsedFile -> {
        CPPParser parser = new CPPParser();
        parser.parse(parsedFile);
    }),
    C(parsedFile -> {
        CParser parser = new CParser();
        parser.parse(parsedFile);
    }),
    JAVASCRIPT(parsedFile -> {
        JavaScriptParser parser = new JavaScriptParser();
        parser.parse(parsedFile);
    }),
    OTHER(parsedFile -> {
        OtherParser parser = new OtherParser();
        parser.parse(parsedFile);
    });
    
    private final LanguageParser languageParser;
    
    ProgrammingLanguage(final LanguageParser languageParser){
        this.languageParser = languageParser;
    }
    
    @Override
    public void parse(final ParsedFile parsedFile) {
        this.languageParser.parse(parsedFile);
    }
}
