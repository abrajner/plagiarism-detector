package com.abrajner.plagiarismdetector.fileparser.languageparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.abrajner.plagiarismdetector.fileparser.ParsedFile;

public class CParser implements LanguageParser{
    
    private ParsedFile parsedFile;
    
    private final List<String> identifiers = new ArrayList<>();
    
    final private static List<String> RETURNED_TYPES = Arrays.asList("auto", "double", "int", "struct", "long", "enum",
            "char", "union", "signed", "void", "float", "short", "unsigned", "bool");
    
    final private static List<String> KEYWORDS = new ArrayList<>(RETURNED_TYPES);
    
    @Override
    public void parse(final ParsedFile parsedFile) {
        KEYWORDS.addAll(Arrays.asList("{", "}", "[", "]", ";", "=", "(", ")", ",", "true",
                "false", "null", "*", "&", "typedef", "static", "const", "<", ">", "."));
        this.parsedFile = parsedFile;
        this.identifiers.addAll(parsedFile.getIdentifiersByEquals());
        parsedFile.getFileContentByInstructions().addAll(this.instructionsParser());
        parsedFile.getFileContentByFunctions().addAll(this.functionsParser());
        this.parsedFile.getIdentifiers().addAll(this.identifiers);
    }
    
    private List<List<String>> instructionsParser() {
        return CommonParser.getInstructionsFromFile(this.parsedFile);
    }
    
    private List<List<String>> functionsParser() {
        return CommonParser.functionsParser(this.parsedFile, RETURNED_TYPES, this.identifiers, KEYWORDS);
    }
}
