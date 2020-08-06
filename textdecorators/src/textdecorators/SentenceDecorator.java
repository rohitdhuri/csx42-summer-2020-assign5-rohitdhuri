package textdecorators;

import java.util.ArrayList;

import textdecorators.util.InputDetails;
import textdecorators.util.StringOperations;

public class SentenceDecorator extends AbstractTextDecorator{

    private AbstractTextDecorator atd;
    private InputDetails id;
    private StringOperations sOp;


    public SentenceDecorator(AbstractTextDecorator atd, InputDetails id){
        this.atd = atd;
        this.id = id;
        sOp = new StringOperations();
    }

    @Override
    public void processInputDetails() {

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
        id.update(newLines);

        newLines = new ArrayList<String>();



        lines = id.getLine();

        for(String line: lines){
            int i = 0;
            ArrayList<Integer> indexes = sOp.findChar(line , ".");

            int count = indexes.size();
            for (i=0; i<count; i++) {
                ArrayList<Integer> newIndexes = sOp.findChar(line.toLowerCase(), ".");
                int pos = newIndexes.get(i);
                line = sOp.insertString(line, "BEGIN_SENTENCE__", pos);
            }
            newLines.add(line);
    }
    newLines.set(0, "BEGIN_SENTENCE__"+newLines.get(0));
    int endIndex = newLines.get(newLines.size()-1).length()-16;
    newLines.set(newLines.size()-1, newLines.get(newLines.size()-1).substring(0, endIndex));
    id.update(newLines);
    System.out.println(id.getLine());


        if (null != atd) {
            atd.processInputDetails();
            }
    }
    
}