package textdecorators.driver;

import java.io.FileNotFoundException;
import java.io.IOException;

import textdecorators.AbstractTextDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.util.InputDetails;
import textdecorators.FileDisplayInterface;
import textdecorators.MyLogger;
import textdecorators.util.exceptions.EmptyFileException;
import textdecorators.util.exceptions.SameFileName;
import textdecorators.util.exceptions.InvalidInputFormat;

public class Driver {
    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

    public static void main(String[] args) {

        if ((args.length != REQUIRED_NUMBER_OF_CMDLINE_ARGS) || (args[0].equals("${input}"))
                || (args[1].equals("${mispelled}")) || (args[2].equals("${keywords}")) || (args[3].equals("${output}"))
                || (args[4].equals("${debug}"))) {
            System.err.println("Error: Incorrect number of arguments. Program accepts "
                    + REQUIRED_NUMBER_OF_CMDLINE_ARGS + " arguments.");
            System.exit(0);
        }

        try{

            if(args[0].equals(args[1]) || args[0].equals(args[2]))
                throw new SameFileName("Same File Name");

            if(args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty())
                throw new FileNotFoundException();


            MyLogger ml = MyLogger.getInstance();
            ml.setDebugValue(Integer.parseInt(args[4]));
            ml.writeMessage("Setting debug level " + args[4], MyLogger.DebugLevel.DRIVER);

            InputDetails inputD = new InputDetails(args[0], args[3]);
            AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
            AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD, args[1]);
            AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD, args[2]);
            AbstractTextDecorator mostFrequentWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);

            ml.writeMessage("Calling processInputDetails method", MyLogger.DebugLevel.DRIVER);
            mostFrequentWordDecorator.processInputDetails();
            
            ml.writeMessage("Calling writeToFile method", MyLogger.DebugLevel.DRIVER);
            ((FileDisplayInterface) inputD).writeToFile();

            ml.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        catch(SameFileName | InvalidInputFormat e){
            System.out.println(e.getMessage());
        }
    }
}