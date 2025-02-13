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
   * @param text the Scanner to read the input text from
   * @return a list of tokens, where each token is a word or a period
   */
  @Override
  public List<String> tokenize(Scanner text) {
    //returned array
    ArrayList<String> tokened = new ArrayList<>();
    // loop through the text
    while(text.hasNext()){
      // make the text lowercase
      String token = text.next().toLowerCase();
      // if a word has a period and its not at the end of the word.
      if(token.contains("." ) && token.substring(token.length()-1).equals(".")){
        // add the substring of the token that does not contain the period
        tokened.add(token.substring(0, token.length()-1));
        // add a period
        tokened.add(".");
      }
      // if the word has no period
      else{
        // just add the word
      tokened.add(token);
      }
    }
    //
    return tokened;
  }
}

