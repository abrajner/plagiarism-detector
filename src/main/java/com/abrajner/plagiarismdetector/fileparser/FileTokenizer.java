package com.abrajner.plagiarismdetector.fileparser;

import java.io.File;
import java.io.FileReader;
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
    
    public FileTokenizer() {
    }
    
    public List<Object> getAllIds() {
        return Collections.unmodifiableList(this.allIds);
    }
    
    public List<Object> tokenize(final MultipartFile file, final ProgrammingLanguage programmingLanguage) {
        try {
            this.fileReader = new InputStreamReader(file.getInputStream());
            this.streamTokenizer = new StreamTokenizer(this.fileReader);
            this.setCommentChar(programmingLanguage);
            this.streamTokenizer.ordinaryChar('/');
            this.streamTokenizer.ordinaryChar(' ');
            this.streamTokenizer.ordinaryChar('\t');
            this.streamTokenizer.eolIsSignificant(true);
            this.currentToken = this.streamTokenizer.nextToken();
            while (this.currentToken != StreamTokenizer.TT_EOF) {
                this.executeWhenUnderscoreToken();
                this.executeStandardTokenBehaviour();
                this.currentToken = this.streamTokenizer.nextToken();
            }
            this.fileReader.close();
        }            catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableList(this.tokens);
    }
    
    private void executeStandardTokenBehaviour(){
        if(this.streamTokenizer.ttype == '='){
            if(!this.tokens.isEmpty()){
                final Object previousToken = this.tokens.get(this.tokens.size() - 1);
                if(previousToken.equals(' ')){
                     int i = this.tokens.size() - 2;
                     while(i>=0){
                         if(!this.tokens.get(i).equals(' ') && this.tokens.get(i) instanceof String && !this.allIds.contains(this.tokens.get(i))){
                             this.allIds.add(this.tokens.get(i).toString());
                             break;
                         }
                         i--;
                     }
                }
                if ((previousToken.equals(' ') || previousToken instanceof String) && !this.allIds.contains(previousToken)) {
                    this.allIds.add(previousToken.toString());
                }
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
        if(programmingLanguage == ProgrammingLanguage.PYTHON) {
            this.streamTokenizer.commentChar('#');
            this.streamTokenizer.slashSlashComments(false);
        }
        if(programmingLanguage == ProgrammingLanguage.C
                || programmingLanguage == ProgrammingLanguage.CPP
                || programmingLanguage == ProgrammingLanguage.JAVA
                || programmingLanguage == ProgrammingLanguage.JAVASCRIPT){
            this.streamTokenizer.slashSlashComments(true);
        }
    }
}
