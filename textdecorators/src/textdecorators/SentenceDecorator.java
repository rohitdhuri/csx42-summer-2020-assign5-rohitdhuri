package textdecorators;

import java.util.ArrayList;

import java.io.IOException;
import textdecorators.util.InputDetails;
import textdecorators.util.StringOperations;

/**
 * Class contains the implementation of processinputdetails method to highlight the beginning and end of a sentence
 * 
 * @author - Rohit Mahendra Dhuri 
 * 
 */
public class SentenceDecorator extends AbstractTextDecorator{

    private AbstractTextDecorator atd;
    private InputDetails id;
    private StringOperations sOp;

  /**
   * Constructor initializes data members
   * @param atd - object of AbstractTextDecorator class
   * @param id - object of InputDetails class
   */
    public SentenceDecorator(AbstractTextDecorator atd, InputDetails id) throws IOException {
        MyLogger.getInstance().writeMessage("SentenceDecorator Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
        this.atd = atd;
        this.id = id;
        sOp = new StringOperations();
    }

    @Override
    public void processInputDetails() throws IOException{

        ArrayList<String> newLines = new ArrayList<String>();
        ArrayList<String> lines = id.getLine();


        for(String line: lines){
            int i = 0;
            ArrayList<Integer> indexes = sOp.findChar(line , ".");

            int count = indexes.size();
            for (i=0; i<count; i++) {
                ArrayList<Integer> newIndexes = sOp.findChar(line.toLowerCase(), ".");
                int pos = newIndexes.get(i);
                line = sOp.insertString(line, "__END_SENTENCE", pos-1);
            }
            newLines.add(line);
    }
        MyLogger.getInstance().writeMessage("Calling update", MyLogger.DebugLevel.SENTENCE_DECORATOR);
        id.update(newLines);

        newLines = new ArrayList<String>();

        MyLogger.getInstance().writeMessage("Calling getLine", MyLogger.DebugLevel.SENTENCE_DECORATOR);
        lines = id.getLine();

        for(String line: lines){
            int i = 0;
            ArrayList<Integer> indexes = sOp.findChar(line , ".");

            int count = indexes.size();
            for (i=0; i<count; i++) {
                MyLogger.getInstance().writeMessage("Calling findchar", MyLogger.DebugLevel.SENTENCE_DECORATOR);
                ArrayList<Integer> newIndexes = sOp.findChar(line.toLowerCase(), ".");
                int pos = newIndexes.get(i);
                MyLogger.getInstance().writeMessage("Calling insertString", MyLogger.DebugLevel.SENTENCE_DECORATOR);
                line = sOp.insertString(line, "BEGIN_SENTENCE__", pos);
            }
            newLines.add(line);
    }
    newLines.set(0, "BEGIN_SENTENCE__"+newLines.get(0));
    int endIndex = newLines.get(newLines.size()-1).length()-16;
    newLines.set(newLines.size()-1, newLines.get(newLines.size()-1).substring(0, endIndex));
    MyLogger.getInstance().writeMessage("Calling update", MyLogger.DebugLevel.SENTENCE_DECORATOR);
    id.update(newLines);

        if (null != atd) {
            atd.processInputDetails();
            }
    }

    @Override
    public String toString() {
        return "Class: SentenceDecorator, Data Members: ["+"AbstractTextDecorator "+ atd+ " ,InputDetails "+ id +" ,StringOperations "+sOp +"]";
    }
    
}