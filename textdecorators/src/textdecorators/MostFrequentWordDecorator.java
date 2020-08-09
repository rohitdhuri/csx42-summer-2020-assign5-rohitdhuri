package textdecorators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import textdecorators.util.InputDetails;
import textdecorators.util.StringOperations;

/**
 * Class contains the implementation of processinputdetails method to highlight most frequent words
 *  in output
 * 
 * @author - Rohit Mahendra Dhuri 
 * 
 */
public class MostFrequentWordDecorator extends AbstractTextDecorator {
  private AbstractTextDecorator atd;
  private InputDetails id;
  private StringOperations sOp;

  /**
   * Constructor initializes data members
   * @param atd - object of AbstractTextDecorator class
   * @param id - object of InputDetails class
   */
  public MostFrequentWordDecorator(AbstractTextDecorator atd, InputDetails id) throws IOException {
    MyLogger.getInstance().writeMessage("MostFrequentWordDecorator constructor", MyLogger.DebugLevel.CONSTRUCTOR);
    this.atd = atd;
    this.id = id;
    sOp = new StringOperations();
  }

  @Override
  public void processInputDetails() throws IOException{
    ArrayList<String> words = new ArrayList<String>();
    HashMap<String, Integer> wordCount = new HashMap<String, Integer>();

    for (String line : id.getLine()) {
      String tokens[] = line
        .replace(".", "")
        .replace(",", "")
        .toLowerCase()
        .trim()
        .split("\\s+");
      for (String str : tokens) words.add(str);
    }

    for (String str : words) {
      if (wordCount.containsKey(str)) 
        wordCount.put(  str,  wordCount.get(str) + 1  );
      else wordCount.put(str, 1);
    }

    String mostFrequentWord = "";
    Integer max = 0;

    for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
      if (entry.getValue() > max) {
        mostFrequentWord = entry.getKey();
        max = entry.getValue();
      }
    }

    //System.out.println(mostFrequentWord);
    ArrayList<String> lines = id.getLine();
    ArrayList<String> newLines = new ArrayList<String>();

    for (String line : lines) {
      MyLogger.getInstance().writeMessage("Calling addNotation", MyLogger.DebugLevel.MOST_FREQUENT_WORD_DECORATOR);
      line = sOp.addNotation(line, mostFrequentWord, "MOST_FREQUENT");
      newLines.add(line);
      //System.out.println(line);
    }
    id.update(newLines);

    if (null != atd) {
      MyLogger.getInstance().writeMessage("Calling processInputDetails", MyLogger.DebugLevel.MOST_FREQUENT_WORD_DECORATOR);
      atd.processInputDetails();
    }
  }

  @Override
  public String toString() {
      return "Class: MostFrequentWordDecorator, Data Members: ["+"AbstractTextDecorator "+ atd+ " ,InputDetails "+ id +" ,StringOperations "+sOp +"]";
  }

}
