package textdecorators.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StringOperations {
    


  public  String insertString(String line, String key, int index) {
    String newString = new String();

    if (index == 0) {
        for (int i = 0; i < line.length(); i++) {
            if (i == index)
                newString += key;
            newString += line.charAt(i);

        }
    } else {
        for (int i = 0; i < line.length(); i++) {
            newString += line.charAt(i);
            if (i == index)
                newString += key;
        }
    }
    return newString;
}

public String addNotation(String line, String word, String notation) {
  int indexesCount = findIndex(line.toLowerCase(), word).size();
  int i = 0;
  for (int k = 0; k < indexesCount; k++) {
    ArrayList<Integer> newIndexes = findIndex(line.toLowerCase(), word);
    int pos = newIndexes.get(i);
    String firstHalf = line.substring(0, pos);
    String secondHalf = line.substring(pos + word.length());
    String key = line.substring(pos, pos+word.length());
    line = firstHalf + notation + "_" + key + "_" + notation + secondHalf;
    i++;
  }
  return line;
}

    public ArrayList<Integer> findChar(String line, String ch) {
        ArrayList<Integer> op = new ArrayList<Integer>();
        int index = line.indexOf(ch);
        while (index > -1) {
            op.add(index);
            index = line.indexOf(ch, index + 1);
        }

        return op;

    }

    public ArrayList<Integer> findIndex(String line, String word) {
      ArrayList<Integer> indexes = new ArrayList<Integer>();
      int currIndex;
      String inputLine = line;
  
      currIndex = findFirstIndex(inputLine, word);
  
      while (currIndex != -1) {
        if (indexes.size() > 0)
          indexes.add(currIndex + word.length() + indexes.get(indexes.size() - 1));
        else
          indexes.add(currIndex);
        inputLine = inputLine.substring(currIndex + word.length());
        currIndex = findFirstIndex(inputLine, word);
      }
  
      return indexes;
    }

    public static int findFirstIndex(String line, String word) {

      int op = -1;
      int initialIndex = -99;
      String t;
  
      while (op == -1) {
        if (line.indexOf(word) == -1)
          return -1;
  
        if (initialIndex == -99) {
          initialIndex = line.indexOf(word);
          if (initialIndex == -1)
            return -1;
        }
  
        else {
          int tlength = line.substring(initialIndex, line.indexOf(" ", initialIndex)).length();
          initialIndex = line.indexOf(word, initialIndex + tlength);
          if (initialIndex == -1)
            return -1;
        }
        if (line.substring(initialIndex).contains(" "))
          t = line.substring(initialIndex, line.indexOf(" ", initialIndex));
        else
          t = line.substring(initialIndex);
  
        if (t.contains("_")) {
          String t2 = t;
          t2 = t2.substring(0, t2.indexOf("_"));
          if (t2.equals(word)) {
            op = initialIndex;
            break;
          }
        } else if (t.contains(",")) {
          String t2 = t;
          t2 = t2.substring(0, t2.indexOf(","));
          if (t2.equals(word)) {
            op = initialIndex;
            break;
          }
        } else if (t.contains(".")) {
          String t2 = t;
          t2 = t2.substring(0, t2.indexOf("."));
          if (t2.equals(word)) {
            op = initialIndex;
            break;
          }
        } else if (t.equals(word)) {
          op = initialIndex;
          break;
        }
      }
      return op;
    }
  }
  

