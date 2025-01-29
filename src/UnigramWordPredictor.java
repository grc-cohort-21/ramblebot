/**
 * @author Shawn Nguru 
 * SDEV 301 RambleBot
 * 1-21-25 
 * 
 */

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
   * For example:
   * If the input text is: "The cat sat. The cat slept. The dog barked."
   * After tokenizing, the tokens would be: ["the", "cat", "sat", ".", "the", "cat", "slept", ".", "the", "dog", "barked", "."]
   * 
   * key: "the"  value: ["cat","cat","dog"]
   * key: "cat"  value: ["sat", "slept"]
   * 
   * 
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
   * The order of the map and the order of each list is not important.
   * 
   * @param scanner the Scanner to read the training text from
   */
  public void train(Scanner scanner) {
    List<String> trainingWords = tokenizer.tokenize(scanner);
    List<String> valueWords = new ArrayList<>();
    neighborMap = new HashMap<>();

    String keyText = "";
    for(int i = 0; i < trainingWords.size(); i++)
    {
      keyText = trainingWords.get(i);
      for(int  j= 0; j < trainingWords.size()-1; j++)
      {
        if(keyText.equals(trainingWords.get(j)))
        {
          valueWords.add(trainingWords.get(j+1));
        }
      }
      neighborMap.put(trainingWords.get(i), valueWords);
      valueWords = new ArrayList<String>();
    }
  }

  /**
   *
   * 
  the quick fox the slow dog the slow cat
predictor.train()
  | 
  v
this.neighborMap = {
    "the": ["quick", "slow", "slow"],
    "quick": ["fox"]
    "slow": ["dog", "cat"]
}

---------------

predictor.predictNextWord(["I", "saw", "the"])
followingWords = neighborMap.get("the") -> ["quick", "slow", "slow"]

rng(3) -> # from [0-2] 
randomNumber = rng(3)
followingWords.get(randomNumber) -> ?
     * 
     *    * 
   * 
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
   * 
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
  public String predictNextWord(List<String> context) 
  {
    // TODO: Return a predicted word given the words preceding it
    /**
     * Count the amount of times each word appears in the List
     * base case if no count is found 
     * divide the count by the total amount in the list
     * 
     */

    String probableWord = "";

    String text = context.get(context.size()-1);
    
    List<String> probaleList = new ArrayList<>();    
    
    for (String key : neighborMap.keySet())     
    {
      if(text == key)
      {
        probaleList = neighborMap.get(key);
      }
    }

    Random wordProb = new Random();
    int max = probaleList.size()-1;

    for (int i = 0; i < probaleList.size(); i++)     
    {
      probableWord = probaleList.get(wordProb.nextInt(max - 0 + 1));
    }
    
  return probableWord;
  }
  
  /**
   * Returns a copy of the neighbor map. The neighbor map is a mapping 
   * from each word to a list of words that have followed it in the training data.
   * 
   * You do not need to modify this method for your project.
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
