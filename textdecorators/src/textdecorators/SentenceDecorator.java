package textdecorators;

import java.util.ArrayList;

import textdecorators.util.InputDetails;

public class SentenceDecorator extends AbstractTextDecorator{

    private AbstractTextDecorator atd;
    private util.InputDetails id;
    private StringOperations sOp;


    public SentenceDecorator(AbstractTextDecorator atd, InputDetails id){
        this.atd = atd;
        this.id = id;
    }

    @Override
    public void processInputDetails() {
        
        ArrayList<String> lines = id.getLine();
        
        
    
        if (null != atd) {
            atd.processInputDetails();
            }
        

    }
    
}