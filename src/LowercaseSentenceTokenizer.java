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

    while (scanner.hasNextLine()) {
      String sentence = scanner.nextLine().toLowerCase();

      // Split the sentence by whitespace (s+ means one or more)
      String[] words = sentence.split("\\s+");

      for (String word : words) {
        // Skip empty Strings
        if (word.isEmpty()) {
          continue; 
        }

        // Checks if word ends with period but has more after like Dr.Smith
        // Went against your recommendation and spent to much time worrying about periods LOL
        if (word.length() > 1 && word.endsWith(".")) {
          String withoutPeriod = word.substring(0,word.length()-1);

          if (withoutPeriod.contains(".")) {
            // keep words that like dr.smith
            tokens.add(word);
          } else {
            // split regular words ending with period as two separate elements
            // This logics pretty funny
            tokens.add(withoutPeriod);
            tokens.add(".");
          }
        }      
        //add normal words that don't end with period
        else {
          tokens.add(word);
        }  
      }  
    }

    return tokens;
  }
}

