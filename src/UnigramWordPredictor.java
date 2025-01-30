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
  private Random random; 

  /**
   * Constructs a UnigramWordPredictor with the specified tokenizer.
   * 
   * @param tokenizer the tokenizer used to process the input text
   */
  public UnigramWordPredictor(Tokenizer tokenizer) {
    this.tokenizer = tokenizer;
    this.neighborMap = new HashMap<>(); 
    this.random = new Random(); 
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

    if (trainingWords.isEmpty()) {
        return;
    }

    for (int i = 0; i < trainingWords.size() - 1; i++) {
      String currentWord = trainingWords.get(i);
      String nextWord = trainingWords.get(i + 1);

      // this code mean ...if currentWord is not already in neighborMap, add it with an empty list as its value.


      neighborMap.putIfAbsent(currentWord, new ArrayList<>());

      // Store the word that follows
      neighborMap.get(currentWord).add(nextWord);
    }
  }

  /**
   * Predicts the next word based on the given context.
   * The prediction is made by randomly selecting from all words 
   * that follow the last word in the context in the training data.
   * 
   * For example:
   * If the input text is: "The cat sat. The cat slept. The dog barked."
   * 
   * The resulting map (neighborMap) would be:
   * {
   *   "the" -> ["cat", "cat", "dog"],
   *   "cat" -> ["sat", "slept"],
   *   "sat" -> ["."],
   *   "." -> ["the", "the"],
   *   "slept" -> ["."],
   *   "dog" -> ["barked"],
   *   "barked" -> ["."]
   * }
   * 
   * When predicting the next word given a context, the predictor should use 
   * the neighbor map to select a word based on the observed frequencies in 
   * the training data. For example:
   * 
   * - If the last word in the context is "the", the next word should be randomly chosen 
   *   from ["cat", "cat", "dog"]. In this case, "cat" has a 2/3 probability 
   *   of being selected, and "dog" has a 1/3 probability, reflecting the 
   *   original distribution of words following "the" in the text.
   * 
   * - If the last word in the context is "cat", the next word should be randomly chosen 
   *   from ["sat", "slept"], giving each an equal 1/2 probability.
   * 
   * - If the last word in the context is ".", the next word should be randomly chosen 
   *   from ["the", "the"], meaning "the" will always be selected 
   *   since it's the only option.
   * 
   * - If the last word in the context is "dog", the next word should be "barked" because 
   *   "barked" is the only word that follows "dog" in the training data.
   * 
   * The probabilities of selecting each word should match the relative 
   * frequencies of the words that follow in the original training data. 
   * 
   * @param context a list of words representing the current context
   * @return the predicted next word, or null if no prediction can be made
   */

   /* explain the code what do ..The predictNextWord method takes a list of
    words as context and predicts the next word. It first checks if the context is empty and
     returns null if so. Then, it retrieves the last word from the list and looks it up in neighborMap, 
     which stores words and their possible next words from training data. If the last word exists in the map 
     and has a list of next words, it randomly selects one based on frequency. If no match is found, it returns
      null.
 */
/* I ues this link to learn on this https://stackoverflow.com/questions/4672806/java-simplest-way-to-get-last-word-in-a-string */
  public String predictNextWord(List<String> context) {
    if (context.isEmpty()) {
        return null;
    }

    String lastWord = context.get(context.size() - 1); // Get last word

    if (neighborMap.containsKey(lastWord)) {
        List<String> nextWords = neighborMap.get(lastWord);

        if (!nextWords.isEmpty()) {
            return nextWords.get(random.nextInt(nextWords.size())); 
        }
    }

    return null; 
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
