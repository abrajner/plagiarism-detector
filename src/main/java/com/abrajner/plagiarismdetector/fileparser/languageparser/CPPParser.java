package com.abrajner.plagiarismdetector.fileparser.languageparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.abrajner.plagiarismdetector.fileparser.ParsedFile;

public class CPPParser implements LanguageParser{
    private ParsedFile parsedFile;
    
    private final List<String> identifiers = new ArrayList<>();
    
    final private static List<String> RETURNED_TYPES = Arrays.asList("int", "void", "double", "long", "char",
            "float", "enum", "short", "struct", "union", "bool", "namespace", "auto", "unsigned", "signed");
    
    final private static List<String> KEYWORDS = new ArrayList<>(RETURNED_TYPES);
    
    @Override
    public void parse(final ParsedFile parsedFile) {
        KEYWORDS.addAll(Arrays.asList("{", "}", "[", "]", ";", "=", "(", ")", ",", "public", "private", "protected",
                "new", "typedef", "class", "this", ".", "const", "dynamic_cast", "explicit", "mutable", "operator", "sizeof",
                "static", "template", "true", "false", "null", "<", ">", "&"));
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
