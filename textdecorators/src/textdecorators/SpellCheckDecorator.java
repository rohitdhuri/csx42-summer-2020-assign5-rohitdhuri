package textdecorators;

import java.io.IOException;
import java.io.FileNotFoundException;


import java.util.ArrayList;
import textdecorators.util.InputDetails;
import textdecorators.util.FileProcessor;
import textdecorators.util.StringOperations;

public class SpellCheckDecorator extends AbstractTextDecorator {

    private AbstractTextDecorator atd;
    private InputDetails id;
    private FileProcessor fp;
    private StringOperations sOp;

    public SpellCheckDecorator(AbstractTextDecorator atd, InputDetails id, String filePath){
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
    public void processInputDetails() {
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
        line = sOp.addNotation(line, mispelled, "SPELLCHECK_");
        newLines.add(line);
        }
        id.update(newLines);
        newLines = new ArrayList<String>();
    }

        if (null != atd) {
            atd.processInputDetails();
        }
    }
    
}