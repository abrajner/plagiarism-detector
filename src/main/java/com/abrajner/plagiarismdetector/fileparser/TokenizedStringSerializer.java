package com.abrajner.plagiarismdetector.fileparser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

public class TokenizedStringSerializer {
    
    public static String serialize(final MultipartFile file, final ProgrammingLanguage programmingLanguage){
        final FileTokenizer fileTokenizer = new FileTokenizer();
        final List<String> listOfTokens = fileTokenizer.tokenize(file, programmingLanguage).stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        
        final ParsedFile parsedFile = new ParsedFile();
        parsedFile.getFileContent().addAll(listOfTokens);
        fileTokenizer.getAllIds().forEach(id -> parsedFile.getIdentifiersByEquals().add(id.toString()));
        programmingLanguage.parse(parsedFile);

        return createJsonObjectFromParsedFile(parsedFile).toString();
    }
    
    private static JSONObject createJsonObjectFromParsedFile(final ParsedFile parsedFile){
        final JSONArray fileContentByFunctions = new JSONArray();
        parsedFile.getFileContentByFunctions().forEach(function -> {
            final JSONArray functionTokens = new JSONArray();
            functionTokens.addAll(function);
            fileContentByFunctions.add(functionTokens);
        });
    
        final JSONArray fileContentByInstructions = new JSONArray();
        parsedFile.getFileContentByInstructions().forEach(instruction -> {
            final JSONArray instructionTokens = new JSONArray();
            instructionTokens.addAll(instruction);
            fileContentByInstructions.add(instructionTokens);
        });
        
        final JSONArray fileIdentifiersByParser = new JSONArray();
        fileIdentifiersByParser.addAll(parsedFile.getIdentifiers());
    
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("contentByFunctions", fileContentByFunctions);
        jsonObject.put("contentByInstructions", fileContentByInstructions);
        jsonObject.put("identifiers", fileIdentifiersByParser);
        
        return jsonObject;
    }
    
    public static ParsedFile deserialize(final String serializedData){
        final JSONParser parser = new JSONParser();
        final  ParsedFile parsedFile = new ParsedFile();
        try {
            final JSONObject jsonObject = (JSONObject) parser.parse(serializedData);
            parsedFile.getIdentifiers()
                    .addAll(parseJsonArrayToArrayList((JSONArray) jsonObject.get("identifiers")));
            parsedFile.getFileContentByInstructions()
                    .addAll(parseNestedJsonArrayToArrayList((JSONArray) jsonObject.get("contentByInstructions")));
            parsedFile.getFileContentByFunctions()
                    .addAll(parseNestedJsonArrayToArrayList((JSONArray) jsonObject.get("contentByFunctions")));
        }
        catch (final ParseException e) {
            throw new RuntimeException("Couldn't parse Json value from database");
        }
    
        return parsedFile;
    }
    
    private static List<List<String>> parseNestedJsonArrayToArrayList(final JSONArray jsonArray){
        final List<List<String>> list = new ArrayList<>();
        for (final Object o : jsonArray) {
            list.add(parseJsonArrayToArrayList((JSONArray) o));
        }
        return list;
    }
    
    private static List<String> parseJsonArrayToArrayList(final JSONArray jsonArray){
        final List<String> list = new ArrayList<>();
        for (final Object o : jsonArray) {
            list.add((String) o);
        }
        return list;
    }
}
