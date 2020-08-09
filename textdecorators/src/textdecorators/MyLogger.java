package textdecorators;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


public class MyLogger {
    /** Enums for specifying debugLevel */
    public enum DebugLevel {
        CONSTRUCTOR, FILE_PROCESSOR, DRIVER, INPUT_DETAILS, STRING_OPERATIONS, KEYWORD_DECORATOR,
         MOST_FREQUENT_WORD_DECORATOR, SENTENCE_DECORATOR, SPELL_CHECK_DECORATOR, NONE
    };

    private DebugLevel debugLevel;
    private static MyLogger uniqueInstance = null;
    private File outputFile = new File("log");
    private BufferedWriter outputBufferedWriter;

    public static MyLogger getInstance() {

        if (uniqueInstance == null)
            uniqueInstance = new MyLogger();

        return uniqueInstance;
    }

    private MyLogger() {
    }

    public void setDebugValue(int levelIn) {
        switch (levelIn) {

            case 9:
                debugLevel = DebugLevel.SPELL_CHECK_DECORATOR;
                break;
            case 8:
                debugLevel = DebugLevel.SENTENCE_DECORATOR;
                break;
            case 7:
                debugLevel = DebugLevel.MOST_FREQUENT_WORD_DECORATOR;
                break;
            case 6:
                debugLevel = DebugLevel.KEYWORD_DECORATOR;
                break;
            case 5:
                debugLevel = DebugLevel.STRING_OPERATIONS;
                break;
            case 4:
                debugLevel = DebugLevel.INPUT_DETAILS;
                break;
            case 3:
                debugLevel = DebugLevel.DRIVER;
                break;
            case 2:
                debugLevel = DebugLevel.CONSTRUCTOR;
                break;
            case 1:
                debugLevel = DebugLevel.FILE_PROCESSOR;
                break;
            default:
                debugLevel = DebugLevel.NONE;
                break;
        }
    }

    public void setDebugValue(DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    public void writeMessage(String message, DebugLevel levelIn) throws IOException {
        if (levelIn == debugLevel){
            if(!outputFile.exists()){
                outputFile.createNewFile();
            }
            if(outputBufferedWriter == null){
                outputBufferedWriter = new BufferedWriter(new FileWriter(outputFile));
            }
            outputBufferedWriter.write(message+"\n");       
        }
    }

    public void close() throws IOException{
        outputBufferedWriter.close();
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}