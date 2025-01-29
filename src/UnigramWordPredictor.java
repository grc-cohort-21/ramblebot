import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

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
    List<String> tempVals; 
    // TODO: Convert the trainingWords into neighborMap here
    neighborMap = new HashMap<>();
    for(int i=0; i<trainingWords.size(); i++){

      //create a variable to store map keys
      String mKey = trainingWords.get(i);

      //check if the token has been completed and added.
      if(!neighborMap.containsKey(mKey)){
        tempVals = new LinkedList<>();
        //find the token and add the next word to tempVals
        for(int j=0; j<trainingWords.size(); j++){

      //this little if statement was a nightmare for me. I wasn't failing any tests, but my map wasn't correct. I'm not sure if there is a bug in the test
      //I used 12 println statements with descriptions and variables and finally realized my if statement was running false when it should be true.
      //you can probably guess what I did wrong but I could not figure out why it was false. I finally found someone on stack overflow with a similar issue.
      //I forgot Strings are objects and I needed to use .equals() rather than ==
          if(mKey.equals(trainingWords.get(j))  && j != (trainingWords.size()-1)){
            tempVals.add(trainingWords.get(j+1));

            //System.out.println("updating " + mKey + " with " + trainingWords.get(j+1));
            //System.out.println(mKey + " = " + tempVals + " " + i + " " + j);
          }
          
        }
        //add in the token, and list of values stored
        neighborMap.put(mKey, tempVals);
      
      }else{
        
      }
    }
    //System.out.println(getNeighborMap());
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
  public String predictNextWord(List<String> context) {
    List<String> vals = new LinkedList<>();
    String lastWord = context.get(context.size()-1);
    // random number research: https://www.tutorialspoint.com/java/util/random_nextint_inc_exc.htm
    Random picker = new Random();
    String choice ="";
    // TODO: Return a predicted word given the words preceding it
    if(neighborMap.containsKey(lastWord)){
      vals = neighborMap.get(lastWord);
      choice = vals.get(picker.nextInt(vals.size()));
    }

    // Hint: only the last word in context should be looked at
    return choice;
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
    System.out.println(copy);
    return copy;
  }
}
