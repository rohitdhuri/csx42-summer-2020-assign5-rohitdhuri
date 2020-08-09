package textdecorators.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import textdecorators.FileDisplayInterface;
import textdecorators.util.exceptions.InvalidInputFormat;
import java.util.regex.Pattern;

public class InputDetails implements FileDisplayInterface {
    private String inputFileName, outputFileName;
    private ArrayList<String> lines = new ArrayList<String>();
    private Pattern pattern = Pattern.compile("[$&+:;=?@#|/'<>^*()%!-]+");

    public InputDetails(String inputFileName, String outputFileName) throws InvalidInputFormat {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        populateLines();
    }

    @Override
    public void writeToFile(){
        try {
            FileWriter outputFile = new FileWriter(outputFileName);
            for(String line: lines)
                outputFile.write(line);
            outputFile.close();
            System.out.println("Successfully wrote to file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public void update(ArrayList<String> newlines){
        lines = newlines;
    }

    public ArrayList<String> getLine(){
        return lines;
    }


    private void populateLines() throws InvalidInputFormat{
        try {
            FileProcessor fp = new FileProcessor(inputFileName);
            String str = fp.poll();
            if(pattern.matcher(str).find()){
                throw new InvalidInputFormat("Special characetrs");
            }

            while(str!= null){
                lines.add(str);
                str = fp.poll();
            }

        } catch (IOException e) {

        }
    }

}