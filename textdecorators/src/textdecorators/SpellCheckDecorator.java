package textdecorators;

import java.io.IOException;
import java.io.FileNotFoundException;


import java.util.ArrayList;
import textdecorators.util.InputDetails;
import textdecorators.util.FileProcessor;
import textdecorators.util.StringOperations;
import textdecorators.MyLogger;


/**
 * Class contains the implementation of processinputdetails method to highlight the mispelled words of a sentence
 * 
 * @author - Rohit Mahendra Dhuri 
 * 
 */
public class SpellCheckDecorator extends AbstractTextDecorator {

    private AbstractTextDecorator atd;
    private InputDetails id;
    private FileProcessor fp;
    private StringOperations sOp;

    /**
     * Constructor initializes data members
     * @param atd - object of AbstractTextDecorator class
     * @param id - object of InputDetails class
     * @param filePath - String containing filepath for mispelled words file
     */
    public SpellCheckDecorator(AbstractTextDecorator atd, InputDetails id, String filePath) throws IOException {
        MyLogger.getInstance().writeMessage("SpellCheckDecorator Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        this.atd = atd;
        this.id = id;
        sOp = new StringOperations();
        try{
            fp = new FileProcessor(filePath);
        }
        catch(FileNotFoundException e){System.err.println("filenotfound");}
        catch(IOException e){System.err.println("ioexception");}
    }

    @Override
    public void processInputDetails() throws IOException {
        ArrayList<String> newLines = new ArrayList<String>();
        ArrayList<String> mispelledList = new ArrayList<String>();
        ArrayList<String> lines = id.getLine();

        try{
            String str= fp.poll();

            while(str!= null){
                mispelledList.add(str);
                str = fp.poll();
            }    
        }
        catch(IOException e){}
        
       for(String mispelled: mispelledList){
        for(String line: id.getLine()){
        MyLogger.getInstance().writeMessage("Calling addNotation", MyLogger.DebugLevel.SPELL_CHECK_DECORATOR);
        line = sOp.addNotation(line, mispelled, "SPELLCHECK");
        newLines.add(line);
        }
        MyLogger.getInstance().writeMessage("Calling update", MyLogger.DebugLevel.SPELL_CHECK_DECORATOR);
        id.update(newLines);
        newLines = new ArrayList<String>();
    }

        if (null != atd) {
            MyLogger.getInstance().writeMessage("Calling processInputDetails", MyLogger.DebugLevel.SPELL_CHECK_DECORATOR);
            atd.processInputDetails();
        }
    }

    @Override
    public String toString() {
        return "Class: SpellCheckDecorator, Data Members: ["+"AbstractTextDecorator "+ atd+ " ,InputDetails "+ id +" ,StringOperations "+sOp + " ,FilePrcessor "+ fp +"]";
    }
    
}