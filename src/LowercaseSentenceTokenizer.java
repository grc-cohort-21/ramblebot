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

    String inputText = text.toString().toLowerCase().trim();

    Pattern pattern = Pattern.compile("[a-zA-Z0-9']+|\\.");
    Matcher matcher = pattern.matcher(inputText);
    /**(\\w) matches any letter, digit, or underscore, and "+" means one or more occurrences. So, this pattern matches 
     * individual words in the input. I made a Matcher object to find the occurrences of the pattern in the input text.
     * I converted the StringBuilder to a String and trimmed any trailing whitespace.
     * 
     * I just switched the regex pattern to "\\w+|\\.". This pattern handles periods at the end of words. 
     * "\\w+" is used for words(sequences of word characters)
     * "\\." is for the periods(to handle them as separate tokens)
     * source: google search, and https://www.geeksforgeeks.org/regular-expressions-in-java/
     * Basically, regex expressions are used for defining string patterns when you are searching, manipualting,
     * or editing a string in Java.
     * 
     * After testing this in the LowercaseSentenceTokenizerTest.java, the "Dr. Smith" was being treated as separate
     * tokens. Specifically, the period was being treated as a separate token. 
     * To fix this, with research, on google search, I searched what pattern helps input like "Dr. Smith" not be 
     * treated as separate tokens. 
     * It gave me the regex pattern "[a-zA-Z0-9']+|\\." That is how I got that regex pattern. 
     * [a-zA-Z0-9']+ : this part matches words that are made up of letters, both lowercase and uppercase, numbers,
     * and apostrophes. (Like in "Dr.Smith's").
     * \\. : This matches a period when it's a separate token, like at the end of a sentence.
     * 
     */

    while (matcher.find()) {
      tokens.add(matcher.group());
    }
    


    /** 
    StringBuilder currentToken = new StringBuilder();
    for (int i = 0; i < inputText.length(); i++) {
      char currentChar = inputText.charAt(i);
    

      if (Character.isLetterOrDigit(currentChar) || currentChar == '\'') {
          currentToken.append(currentChar);
      }

      else if (currentChar == '.') {
        if (currentToken.length() > 0) {
          tokens.add(currentToken.toString());
          currentToken.setLength(0);
        }
        tokens.add(".");
      }

      else if (Character.isWhitespace(currentChar)) {
        if (currentToken.length() > 0) {
          tokens.add(currentToken.toString());
          currentToken.setLength(0);
        }
      }
    }

    if (currentToken.length() > 0) {
      tokens.add(currentToken.toString());
    }

*I cannot figure out a way to pass the "Dr.Smith's" test:(( It's always treating the period as a separate token.
*I tried using a for loop with else if's also but it's still treating it as a separate token. I don't know how to fix it.
*/

    return tokens;
  }
  

}

