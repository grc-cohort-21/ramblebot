import java.util.ArrayList;
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
    List<String> tokens = new ArrayList<>();
    // reads whole scanner
    while(scanner.hasNextLine()) {
      // stores the line
      String line = scanner.nextLine();
      // makes an array from the line using spaces gets rid of extra spaces with +
      // source for \\s vs \\s+ : https://stackoverflow.com/a/15625711
      String[] list = line.split("\\s+");
      // adds items in array to the list of tokens
      for(int i = 0; i < list.length; i++) {
        String word = list[i];
        //only way I know how to solve the last two test cases
        char last = word.charAt(word.length()-1);
        if(last != '.'){
          tokens.add(word.toLowerCase());
        }
        else{
          String newString = word.substring(0, word.length()-1);
          tokens.add(newString);
          tokens.add(".");
        }
      }
    }
    return tokens;
  }
}

