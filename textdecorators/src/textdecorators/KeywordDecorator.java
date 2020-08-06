package textdecorators;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import textdecorators.util.InputDetails;
import textdecorators.util.StringOperations;
import textdecorators.util.FileProcessor;

public class KeywordDecorator extends AbstractTextDecorator {

    private AbstractTextDecorator atd;
    private InputDetails id;
    private FileProcessor fp;
    private StringOperations sOp;

    public KeywordDecorator(AbstractTextDecorator atd, InputDetails id, String filePath){
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
    public void processInputDetails() {
        ArrayList<String> newLines = new ArrayList<String>();
        ArrayList<String> keywordList = new ArrayList<String>();
        ArrayList<String> lines = id.getLine();

        try{
            String str= fp.poll();

            while(str!= null){
                keywordList.add(str);
                str = fp.poll();
            }    
        }
        catch(IOException e){}

        
            for(String keyword: keywordList){
                for(String line: id.getLine()){
                line = sOp.addNotation(line, keyword, "KEYWORD_");
              //  System.out.println(line);
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