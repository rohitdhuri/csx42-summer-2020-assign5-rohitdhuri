package textdecorators.driver;

import textdecorators.AbstractTextDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.util.InputDetails;

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

        System.out.println("In driver");

        InputDetails inputD = new InputDetails(args[0], args[3]);
        AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
		AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD, args[1]);
		AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD, args[2]);
		AbstractTextDecorator mostFrequentWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);

        mostFrequentWordDecorator.processInputDetails();
        

    }

}