package com.abrajner.plagiarismdetector.fileparser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileTokenizer {
    
    final List<Object> tokens = new ArrayList<>();
    
    private final List<String> allIds = new ArrayList<>();
    
    private Reader fileReader;
    
    private StreamTokenizer streamTokenizer;
    
    private final int QUOTE_CHARACTER = '\'';
    private final int DOUBLE_QUOTE_CHARACTER = '"';
    
    private int currentToken;
    
    public FileTokenizer(final MultipartFile file, final ProgrammingLanguage programmingLanguage) {
        try {
            this.fileReader = new InputStreamReader(file.getInputStream());
            this.streamTokenizer = new StreamTokenizer(this.fileReader);
            this.setCommentChar(programmingLanguage);
            this.streamTokenizer.eolIsSignificant(true);
            this.streamTokenizer.quoteChar('"');
            this.currentToken = this.streamTokenizer.nextToken();
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
    
    public List<Object> getAllIds() {
        return Collections.unmodifiableList(this.allIds);
    }
    
    public List<Object> tokenize() {
        while (this.currentToken != StreamTokenizer.TT_EOF) {
            try {
                this.executeWhenUnderscoreToken();
                this.executeStandardTokenBehaviour();
                this.currentToken = this.streamTokenizer.nextToken();
                this.fileReader.close();
                return Collections.unmodifiableList(this.tokens);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Collections.unmodifiableList(this.tokens);
    }
    
    private void executeStandardTokenBehaviour(){
        if(this.streamTokenizer.ttype == '='){
            if(!this.tokens.isEmpty()){
                Object previousToken = this.tokens.get(this.tokens.size() - 1);
                if (previousToken instanceof String && !this.allIds.contains(previousToken))
                this.allIds.add(this.tokens.get(this.tokens.size() - 1).toString());
            }
        }
        if (this.streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
            this.tokens.add(this.streamTokenizer.nval);
        } else if (this.streamTokenizer.ttype == StreamTokenizer.TT_WORD) {
            this.tokens.add(this.streamTokenizer.sval);
        } else if (this.streamTokenizer.ttype == this.QUOTE_CHARACTER){
            this.tokens.add("'");
            this.tokens.add(this.streamTokenizer.sval);
            this.tokens.add("'");
        } else if (this.streamTokenizer.ttype == this.DOUBLE_QUOTE_CHARACTER){
            this.tokens.add("\"");
            this.tokens.add(this.streamTokenizer.sval);
            this.tokens.add("\"");
        }
        else {
            this.tokens.add((char) this.currentToken);
        }
    }
    
    private void executeWhenUnderscoreToken() throws IOException {
        if(this.streamTokenizer.ttype == '_'){
            this.currentToken = this.streamTokenizer.nextToken();
            final StringBuilder tokenName = new StringBuilder();
            if(!this.tokens.isEmpty()) {
                final Object previousToken = this.tokens.get(this.tokens.size() - 1);
                if (previousToken instanceof String) {
                    this.tokens.remove(this.tokens.size() - 1);
                    tokenName.append(previousToken);
                }
            }
            tokenName.append("_");
            if(this.streamTokenizer.sval != null) {
                tokenName.append(this.streamTokenizer.sval);
                this.currentToken = this.streamTokenizer.nextToken();
            }
            this.tokens.add(tokenName.toString());
        }
    }
    
    private void setCommentChar(final ProgrammingLanguage programmingLanguage){
        if(programmingLanguage == ProgrammingLanguage.PYTHON
                || programmingLanguage == ProgrammingLanguage.PHP) {
            this.streamTokenizer.commentChar('#');
        }
        if(programmingLanguage == ProgrammingLanguage.C
                || programmingLanguage == ProgrammingLanguage.CPP
                || programmingLanguage == ProgrammingLanguage.JAVA
                || programmingLanguage == ProgrammingLanguage.CSHARP
                || programmingLanguage == ProgrammingLanguage.JAVASCRIPT){
            this.streamTokenizer.slashSlashComments(true);
        }
    }
}
