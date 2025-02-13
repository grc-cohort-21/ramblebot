import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A tokenizer that converts text input to lowercase and splits it 
 * into a list of tokens, where each token is either a word or a period.
 */
public class LowercaseSentenceTokenizer implements Tokenizer {
  /**
   * Tokenizes the text from the given Scanner. The method should 
   * convert the text to lowercase and split it into words and periods.
   * Words are separated by spaces, and periods are treated as separate tokens.
   * 
   * For example:
   * If the input text is: "Hello world. This is an example."
   * The tokenized output should be: ["hello", "world", ".", "this", "is", "an", "example", "."]
   * 
   * Notice that the text is converted to lowercase, and each period is treated as a separate token.
   * 
   * However, a period should only be considered a separate token if it occurs at the end
   * of a word. For example:
   * 
   * If the input text is: "Hello world. This is Dr.Smith's example."
   * The tokenized output should be: ["hello", "world", ".", "this", "is", "dr.smith's", "example", "."]
   * 
   * The internal period in Dr.Smith's is not treated as its own token because it does not occur at the end of the word.
   * 
   * @param scanner the Scanner to read the input text from
   * @return a list of tokens, where each token is a word or a period
   */
  public List<String> tokenize(Scanner scanner) {
    // TODO: Implement this function to convert the scanner's input to a list of words and periods
    List<String> intialStep = breakLineDetect(scanner);
    // System.out.println(intialStep.size());
    // System.out.println(intialStep);

    return intialStep;
  }

  public List<String> breakLineDetect(Scanner scanner){
      List<String> holder = new ArrayList<>();
      while(scanner.hasNextLine()){
        String lineHolder = scanner.nextLine();
        holder.add(lineHolder);
      }

      return detectSpacesInLine(holder);
  }

  public List<String> detectSpacesInLine(List<String> processedLines){
      List<String> holder = new ArrayList<>(); //hold each word
      // List<String> lineHolder = new ArrayList<String>(); //list.get(i)
      String lineHolder;

      for(int i = 0; i < processedLines.size();i++){
        lineHolder = processedLines.get(i);
        lineHolder = lineHolder.toLowerCase();
        String[] tempLine = removeAllSpaces(lineHolder);

        holder.addAll(Arrays.asList(tempLine));
      }

      return holder;
  }

  public String[] removeAllSpaces(String lineHolder){
    String[] tempHolder = lineHolder.split(" ");
    List<String> newList = new ArrayList<>();

    for(int i = 0; i < tempHolder.length; i++){
      if(!tempHolder[i].equals("")){
        if(seperatePeriods(tempHolder[i])){
          String grabPeriod = Character.toString(tempHolder[i].charAt(tempHolder[i].length()-1));
          tempHolder[i] = tempHolder[i].substring(0, tempHolder[i].length()-1);
          
          newList.add(tempHolder[i]);
          newList.add(grabPeriod);
        }else{
          newList.add(tempHolder[i]);
        }
      }
    }

    tempHolder = newList.toArray(new String[0]);

    return tempHolder;
  }

  public boolean seperatePeriods(String wordsLastCheck){
    char checkLastChara = wordsLastCheck.charAt(wordsLastCheck.length()-1);
    boolean checkCondit = checkLastChara == '.';
    return checkCondit;
  }
}

