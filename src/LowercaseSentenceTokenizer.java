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
  public List<String> tokenize(Scanner scanner) 
  {
    // TODO: Implement this function to convert the scanner's input to a list of words and periods
    List<String> tokenList = new ArrayList<>();
  
    while(scanner.hasNext())
    {
      String token = scanner.next().toLowerCase();
      
      if (checkIfAllPunctuation(token)) 
      {
        for (char character : token.toCharArray()) 
        {
          String punctuation = String.valueOf(character);
          tokenList.add(punctuation);
        }
        continue;
      }

      if(token.startsWith("\""))
      {
        tokenList.add("\"");
        token = token.substring(1,token.length());
      }

      if(token.endsWith("..."))
      {
        token = token.substring(0,token.length()-3);
        tokenList.add(token);
        tokenList.add("...");
      }
      
      else if (token.length() > 1 && token.endsWith("\"")) 
      {
        char beforeQuote = token.charAt(token.length() - 2);
        if (checkIfPunctuation(beforeQuote)) 
        {
          token = token.substring(0, token.length() - 2);
          tokenList.add(token);
          String punctuation = String.valueOf(beforeQuote);
          tokenList.add(punctuation);
          tokenList.add("\"");
        } 
        
        else 
        {
          token = token.substring(0, token.length() - 1);
          tokenList.add(token);
          tokenList.add("\"");
        }
      }

      else if(token.endsWith("\""))
      {
        token = token.substring(0,token.length()-1);
        tokenList.add(token);
        tokenList.add("\"");
      }
      
      else if(checkIfPunctuation(token.charAt(token.length() - 1)))
      {
        char lastChar = token.charAt(token.length() - 1);
        token = token.substring(0, token.length() - 1);
        tokenList.add(token);
        String last = String.valueOf(lastChar);
        tokenList.add(last);
      }

      else if (checkIfAllPunctuation(token)) 
      {
        for (char character : token.toCharArray()) 
        {
          String punctuation = String.valueOf(character);
          tokenList.add(punctuation);
        }
      }
     
      else
      {
        tokenList.add(token);
      }
    }
    return tokenList;
  }

  public boolean checkIfPunctuation(char character)
  {
    if(character == '.' || character == '!' || character == '?' || character == ',' || character == ';' || character == ':' || character == '\"')
    {
      return true;
    }

    return false;
  }

  public boolean checkIfAllPunctuation(String token)
  {
    for(char character : token.toCharArray())
    if(!checkIfPunctuation(character))
    {
      return false;
    }

    return true;
  }
}

