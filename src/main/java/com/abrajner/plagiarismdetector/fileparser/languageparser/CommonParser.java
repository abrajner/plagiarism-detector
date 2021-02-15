package com.abrajner.plagiarismdetector.fileparser.languageparser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.abrajner.plagiarismdetector.fileparser.ParsedFile;

public class CommonParser {
    
    public static List<List<String>> getInstructionsFromFile(final ParsedFile parsedFile) {
        final List<List<String>> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        result.add(new ArrayList<>());
        while (i < parsedFile.getFileContent().size()) {
            final String token = parsedFile.getFileContent().get(i);
            if (";".equals(token) || "{".equals(token) || "}".equals(token)) {
                result.get(j).add(token);
                result.add(new ArrayList<>());
                j++;
            }
            else if (!"\n".equals(token)) {
                if (!"\t".equals(token)) {
                    result.get(j).add(token);
                }
            }
            else {
                result.add(new ArrayList<>());
                j++;
            }
            i++;
        }
        return result
                .stream()
                .filter(tokens -> !tokens.isEmpty())
                .collect(Collectors.toList());
    }
    
    
}
