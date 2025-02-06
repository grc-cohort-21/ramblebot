import java.util.List;
import java.util.Scanner;

/**
 * An interface representing a tokenizer for processing text input.
 * The implementation should convert the input text into a list of tokens.
 */
public interface Tokenizer {
  /**
   * Tokenizes the text from the given Scanner.
   * 
   * @param scanner the Scanner to read the input text from
   * @return a list of tokens
   */
  public List<String> tokenize(Scanner scanner);
}
