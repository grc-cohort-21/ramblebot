import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * A class for predicting the next word in a sequence using a unigram model.
 * The model is trained on input text and maps each word to a list of 
 * words that directly follow it in the text.
 */
public class UnigramWordPredictor implements WordPredictor {
  private Map<String, List<String>> neighborMap;
  private Tokenizer tokenizer;

  /**
   * Constructs a UnigramWordPredictor with the specified tokenizer.
   * 
   * @param tokenizer the tokenizer used to process the input text
   */
  public UnigramWordPredictor(Tokenizer tokenizer) {
    this.tokenizer = tokenizer;
  }

  /**
   * Trains the predictor using the text provided by the Scanner.
   * The method tokenizes the text and builds a map where each word 
   * is associated with a list of words that immediately follow it 
   * in the text. The resultant map is stored in the neighborMap
   * instance variable.
   * 
   * @param scanner the Scanner to read the training text from
   */
  public void train(Scanner scanner) {
    List<String> trainingWords = tokenizer.tokenize(scanner);
    this.neighborMap = new HashMap<>();
    
    // Itterate over the list except for the last element because nothing comes after it. 
    // If the neighborMap.containsKey(trainingWords(i)) - Where i is the current index (or word) of the trainingWords itteration 
    //      - get the corresponding list and add trainingWords(i+1) becasue you are adding the word that follows
    // Else create a new arrayList, list.add(i+1) - for the word following i - then neighborMap.put(i, newlist) 
    //      - the current word as the key, the new list as the value
    for (int i = 0; i < trainingWords.size() - 1; i++) {
      if (neighborMap.containsKey(trainingWords.get(i))) {
        neighborMap.get(trainingWords.get(i)).add(trainingWords.get(i+1));
      }
      else {
        List<String> newList = new ArrayList<>();
        newList.add(trainingWords.get(i+1));
        neighborMap.put(trainingWords.get(i), newList);
      }
    }

    // Account for the last word not being anywhere else in the training, to not crash the program
    // Loop the training words so that the first word follows the last
    if (!neighborMap.containsKey(trainingWords.get(trainingWords.size() - 1))) {
      List<String> newList = new ArrayList<>();
      newList.add(trainingWords.get(0));
      neighborMap.put(trainingWords.get(trainingWords.size() - 1), newList);
    }
  }

  /**
   * Predicts the next word based on the given context.
   * The prediction is made by randomly selecting from all words 
   * that follow the last word in the context in the training data.
   * 
   * @param context a list of words representing the current context
   * @return the predicted next word, or null if no prediction can be made
   */
  public String predictNextWord(List<String> context) {
    // Examine last word in context
    // Get possible word list from neighbor map
    // Generate a random number between 0 and the size of the posible word list
    // return a word from possible word list at random number index
    String currentWord = context.get(context.size() - 1);
    List<String> possibleNextWords = new ArrayList<>(neighborMap.get(currentWord));
    Random random = new Random();

    return possibleNextWords.get(random.nextInt(possibleNextWords.size()));
  }
  
  /**
   * Returns a copy of the neighbor map. The neighbor map is a mapping 
   * from each word to a list of words that have followed it in the training data.
   * 
   * @return a copy of the neighbor map
   */
  public Map<String, List<String>> getNeighborMap() {
    Map<String, List<String>> copy = new HashMap<>();

    for (Map.Entry<String, List<String>> entry : neighborMap.entrySet()) {
      List<String> newList = new ArrayList<>(entry.getValue());
      copy.put(entry.getKey(), newList);
    }

    return copy;
  }
}
