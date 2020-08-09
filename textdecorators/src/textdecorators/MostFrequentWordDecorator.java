package textdecorators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import textdecorators.util.InputDetails;
import textdecorators.util.StringOperations;

public class MostFrequentWordDecorator extends AbstractTextDecorator {
  private AbstractTextDecorator atd;
  private InputDetails id;
  private StringOperations sOp;

  public MostFrequentWordDecorator(AbstractTextDecorator atd, InputDetails id) {
    this.atd = atd;
    this.id = id;
    sOp = new StringOperations();
  }

  @Override
  public void processInputDetails() {
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
      if (wordCount.containsKey(str)) wordCount.put(
        str,
        wordCount.get(str) + 1
      ); else wordCount.put(str, 1);
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
      line = sOp.addNotation(line, mostFrequentWord, "MOST_FREQUENT");
      newLines.add(line);
      //System.out.println(line);
    }
    id.update(newLines);

    if (null != atd) {
      atd.processInputDetails();
    }
  }
}
