import java.util.List;
import java.util.Scanner;

/**
 * An interface representing a word prediction model.
 * Implementations should train on text data and predict the next word based
 * on the given context.
 * 
 * You do not need to modify this interface for your project.
 */
public interface WordPredictor {
  /**
   * Trains the predictor using the text provided by the Scanner.
   * 
   * @param scanner the Scanner to read the training text from
   * @return 
   */
  public List<String> train(Scanner scanner);
  
  /**
   * Predicts the next word based on the given context.
   * 
   * @param context a list of words representing the current context
   * @return the predicted next word, or null if no prediction can be made
   */
  public String predictNextWord(List<String> context);
}
