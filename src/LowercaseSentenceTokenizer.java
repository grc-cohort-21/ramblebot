
 
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
    /* TODO: Implement this function to convert the scanner's input to a list of words and periods
    return null; */

 
@Override
    public List<String> tokenize(Scanner scanner) {
        List<String> tokens = new ArrayList<>();
        
        String input = scanner.nextLine().toLowerCase();

        // Use "\\s+" to split by any number of spaces (removes empty tokens)
        String[] words = input.split("\\s+"); 
        
        for (String word : words) {
            if (word.endsWith(".")) {
              // I ues the substring in this website https://www.w3schools.com/jsref/jsref_substring.asp
              /* explain what this code do...
              This code reads a sentence, converts it to lowercase, and splits it into words. It then checks
if any word ends with a period (.). If there is a period at the end of a word, the period is
separated and stored as its own token. Anything without a period gets directly added to the list. Finally,
This function separates the words from the period as different tokens in a list.
               */
              String withoutPeriod = word.substring(0, word.length() - 1);
                if (!withoutPeriod.isEmpty()) {
                    tokens.add(withoutPeriod); // Add the word without the period
                }
                tokens.add("."); // Add the period as a separate token
            } else {
                tokens.add(word); 
            }
        }

        return tokens;
    }
}


