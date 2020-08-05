package textdecorators.util;

import java.io.IOException;
import java.util.ArrayList;

public class InputDetails {
    private String inputFileName, outputFileName;
    private ArrayList<String> lines = new ArrayList<String>();

    public InputDetails(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        populateLines();
    }

    public void update(ArrayList<String> newlines){
        lines = newlines;
        System.out.println(lines);
    }

    public ArrayList<String> getLine(){
        return lines;
    }


    private void populateLines() {
        try {
            FileProcessor fp = new FileProcessor(inputFileName);
            String str = fp.poll();

            while(str!= null){
                lines.add(str);
                str = fp.poll();
            }

            // for(String line: lines)
            // System.out.println(line);

        } catch (IOException e) {

        }
    }

}