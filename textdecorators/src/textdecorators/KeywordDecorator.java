package textdecorators;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import textdecorators.util.InputDetails;
import textdecorators.util.StringOperations;
import textdecorators.util.FileProcessor;
import textdecorators.MyLogger;


/**
 * Class contains the implementation of processinputdetails method to highlight keywords in output
 * 
 * @author - Rohit Mahendra Dhuri 
 * 
 */
public class KeywordDecorator extends AbstractTextDecorator {

    private AbstractTextDecorator atd;
    private InputDetails id;
    private FileProcessor fp;
    private StringOperations sOp;

    /**
     * Constructor initalizes data members
     * @param atd - object of AbstractTextDecorator class
     * @param id - object of InputDetails class
     * @param filePath - String caintiang input filepath for keywords file
     */
    public KeywordDecorator (AbstractTextDecorator atd, InputDetails id, String filePath) throws IOException {
        MyLogger.getInstance().writeMessage("InputDetails Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        this.atd = atd;
        this.id = id;
        sOp = new StringOperations();
        try{
            fp = new FileProcessor(filePath);
        }
        catch(FileNotFoundException e){}
        catch(IOException e){}
    }

    @Override
    public void processInputDetails() throws IOException {
        ArrayList<String> newLines = new ArrayList<String>();
        ArrayList<String> keywordList = new ArrayList<String>();
        ArrayList<String> lines = id.getLine();

        try{
            String str= fp.poll();

            while(str!= null){
                MyLogger.getInstance().writeMessage("Calling add", MyLogger.DebugLevel.KEYWORD_DECORATOR);
                keywordList.add(str);
                MyLogger.getInstance().writeMessage("Calling poll", MyLogger.DebugLevel.KEYWORD_DECORATOR);
                str = fp.poll();
            }    
        }
        catch(IOException e){
            e.printStackTrace();        
        }

        
            for(String keyword: keywordList){
                for(String line: id.getLine()){
                MyLogger.getInstance().writeMessage("Calling addNotation", MyLogger.DebugLevel.KEYWORD_DECORATOR);
                line = sOp.addNotation(line, keyword, "KEYWORD");
                newLines.add(line);
                }
                MyLogger.getInstance().writeMessage("Calling update", MyLogger.DebugLevel.KEYWORD_DECORATOR);
                id.update(newLines);
                newLines = new ArrayList<String>();
            }
            
        

    if (null != atd) {
        MyLogger.getInstance().writeMessage("Calling processInputDetails", MyLogger.DebugLevel.KEYWORD_DECORATOR);
        atd.processInputDetails();
        }
    }

    @Override
    public String toString() {
        return "Class: KeywordDecorator, Data Members: ["+"AbstractTextDecorator "+ atd+ " ,InputDetails "+ id +" ,FileProcessor "+fp +"]";
    }
    
}