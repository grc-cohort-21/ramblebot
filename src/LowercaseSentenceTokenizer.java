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
   * @param scanner the Scanner to read the input text from
   * @return a list of tokens, where each token is a word or a period
   */
  public List<String> tokenize(Scanner scanner) {
    List<String> tokens = new ArrayList<>();
    
    while (scanner.hasNext()) {
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

