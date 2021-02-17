package com.abrajner.plagiarismdetector.fileparser.languageparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.abrajner.plagiarismdetector.fileparser.ParsedFile;

public class JavaParser implements LanguageParser {
    
    private ParsedFile parsedFile;
    
    private final List<String> identifiers = new ArrayList<>();
    
    final private static List<String> RETURNED_TYPES = Arrays.asList("int", "Integer", "void", "double", "Double", "long", "Long", "char", "Character",
            "float", "Float", "enum", "short", "Short", "boolean", "Boolean", "Object", "List", "Map", "private", "protected", "public", "static", "byte");
    
    final private static List<String> KEYWORDS = new ArrayList<>(RETURNED_TYPES);
    
    @Override
    public void parse(final ParsedFile parsedFile) {
        KEYWORDS.addAll(Arrays.asList("{", "}", "[", "]", ";", "=", "(", ")", ",", "new", "class", "this", ".", "const",
                "sizeof", "final", "true", "false", "null", "<", ">", "&", "abstract", "extends", "implements", "interface", "super"));
        this.parsedFile = parsedFile;
        this.identifiers.addAll(parsedFile.getIdentifiersByEquals());
        parsedFile.getFileContentByInstructions().addAll(this.instructionsParser());
        parsedFile.getFileContentByFunctions().addAll(this.functionsParser());
        this.parsedFile.getIdentifiersByParser().addAll(this.identifiers);
    }
    
    private List<List<String>> instructionsParser() {
        return CommonParser.getInstructionsFromFile(this.parsedFile);
    }
    
    private List<List<String>> functionsParser() {
        return CommonParser.functionsParser(this.parsedFile, RETURNED_TYPES, this.identifiers, KEYWORDS);
    }
}
