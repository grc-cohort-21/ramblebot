import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**I did some research online asking how I can match words and periods for java and it showed that 
  * regex works really well to match words and periods. Pattern helps match word characters and 
  * Matcher helps find occurrences of the pattern(words) in the input text.
*/

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
    StringBuilder text = new StringBuilder();

    while (scanner.hasNextLine()) {
      text.append(scanner.nextLine()).append(" ");
    }

    Pattern pattern = Pattern.compile("\\w+");
    Matcher matcher = pattern.matcher(text.toString().trim());
    /**(\\w) matches any letter, digit, or underscore, and "+" means one or more occurrences. So, this pattern matches 
     * individual words in the input. I made a Matcher object to find the occurrences of the pattern in the input text.
     * I converted the StringBuilder to a String and trimmed any trailing whitespace.
     */

    while (matcher.find()) {
      tokens.add(matcher.group());
    }

    return tokens;
  }
}

