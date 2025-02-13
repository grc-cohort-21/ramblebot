import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A tokenizer that converts text input to lowercase and splits it 
 * into a list of tokens, where each token is either a word or a period.
 */
public class LowercaseSentenceTokenizer implements Tokenizer 
{
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
  public List<String> tokenize(Scanner scanner) //scanner object named object
  {
    // TODO: Implement this function to convert the scanner's input to a list of words and periods
    List<String> list = new ArrayList<>(); //creating


    while(scanner.hasNext())  //looking at the first word and putting it into the list
    {  
      String word = scanner.next().toLowerCase(); //looks at the beginning of the word to see if it exists then adds with .add. 
      //.toLowerCase sets the string to all lower case letters
      
     if (word.endsWith(".")) //we want to check the period. at the end of the sentence
     {
      list.add(word.substring(0, word.length() -1)); // adding the word w/o period. //set the first one the way you want inclusive. word.length returns the entire word. word.length() - 1 will be exclusive
        list.add(".");//adding the period 
     }
     else
     {
      list.add(word); //now just add the word
     }
    }

    return list;
  }
}

