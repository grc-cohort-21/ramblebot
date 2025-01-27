import java.util.*;

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
    // Create a new ArrayList of Strings - tokens
    List<String> tokens = new ArrayList<>();
    // While scanner.hasNext
    while (scanner.hasNext()) {
      // Store token for period examination
      String token = scanner.next();
      char tokenLastChar = token.charAt(token.length() - 1);
      // Check if last char of token is a period
      // If last char is period, token.charAt(-1) == '.'  then add substring of token.substring(0, token.length() - 1) then token.add(".")
      // Else add token
      if (tokenLastChar == '.') {
        tokens.add(token.substring(0, token.length() - 1).toLowerCase());
        tokens.add(".");
      }
      else {
        tokens.add(token.toLowerCase());
      }
    }
    return tokens;
  }
}

