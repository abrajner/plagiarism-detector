package com.abrajner.plagiarismdetector.fileparser.languageparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.abrajner.plagiarismdetector.fileparser.ParsedFile;

public class CParser implements LanguageParser{
    
    private ParsedFile parsedFile;
    
    private List<String> identifiers = new ArrayList<>();
    
    final private static List<String> RETURNED_TYPE = Arrays.asList("int", "void", "double", "long", "char",
            "float", "enum", "short", "struct", "union", "bool");
    
    final private static List<String> KEYWORDS = new ArrayList<>(RETURNED_TYPE);
    
    @Override
    public void parse(final ParsedFile parsedFile) {
        KEYWORDS.addAll(Arrays.asList("{", "}", "[", "]", ";", "=", "(", ")", ","));
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
        final List<List<String>> result = new ArrayList<>();
        final List<List<String>> fileContent = new ArrayList<>(this.parsedFile.getFileContentByInstructions());
        int k = 0;
        boolean isFunctionEntered = false;
        int numberOfEnteredBrackets = 0;
        result.add(new ArrayList<>());
        for(final List<String> line: fileContent){
            boolean isInstructionWithReturnType = false;
            boolean createNewArrayForFunction = false;
            if(line.contains("(") && line.contains(")") && line.contains("{")){
                createNewArrayForFunction = true;
            }
            for(final String currentValue: line){
                if(RETURNED_TYPE.contains(currentValue)){
                    if(createNewArrayForFunction){
                        createNewArrayForFunction = false;
                        result.add(new ArrayList<>());
                        k ++;
                    }
                    result.get(k).add(currentValue);
                    isInstructionWithReturnType = true;
                    continue;
                }
                if(isInstructionWithReturnType){
                    if(!KEYWORDS.contains(currentValue) && !NumberUtils.isCreatable(currentValue) && !this.identifiers.contains(currentValue)){
                        this.identifiers.add(currentValue);
                    }
                }
                if("{".equals(currentValue) && isInstructionWithReturnType){
                    numberOfEnteredBrackets++;
                    isFunctionEntered = true;
                }
                if ("}".equals(currentValue)){
                    numberOfEnteredBrackets--;
                    if(isFunctionEntered && numberOfEnteredBrackets<1) {
                        result.get(k).add(currentValue);
                        isFunctionEntered = false;
                        continue;
                    }
                }
                result.get(k).add(currentValue);
            }
        }
        return result;
    }
}
