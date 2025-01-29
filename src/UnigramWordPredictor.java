import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * A class for predicting the next word in a sequence using a unigram model.
 * The model is trained on input text and maps each word to a list of 
 * words that directly follow it in the text.
 */
public class UnigramWordPredictor implements WordPredictor 
{
  private Map<String, List<String>> neighborMap; //Map instance variable named neighborhoodMap
  private Tokenizer tokenizer; //instance variable named tokenizer

  /**
   * Constructs a UnigramWordPredictor with the specified tokenizer.
   * 
   * @param tokenizer the tokenizer used to process the input text
   */
  public UnigramWordPredictor(Tokenizer tokenizer) 
  {
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
  public void train(Scanner scanner) //method called train passing in Scanner named scanner
  {
      List<String> trainingWords = tokenizer.tokenize(scanner); //traingWords will contain list of strings from a file, since we cant pass a file. Training words object is now the cup that holds the string of words here..
      neighborMap = new HashMap<>(); //initialize neighborMap...neighborMap is the whole key, value map of strings. 
      //[1][2][3][4][5] number qty size
      //[0][1][2][3][4] number index

      for (int i = 0; i < trainingWords.size(); i++) //iterate through the list             
      {                                                                                     
            String keyWord = trainingWords.get(i); //creating keyword // String keyWordNext = trainingWords.get(i + 1); //creating next keyword

            if(!neighborMap.containsKey(keyWord))
            {

              List<String> keyValue = new ArrayList<>(); //creating a list called keyValue

              for(int j = 0; j < trainingWords.size(); j++)
              {


                  if(keyWord.equals(trainingWords.get(j)) && j + 1 < trainingWords.size()) //checks the values that are in keyWord and trainingWords and see if the values in index j matches. 
                  {
                    int k = j + 1; //storing index j + 1 to a variable
                    keyValue.add(trainingWords.get(k)); //actually doing something after the iterating. with .add by adding the string in the position of keyWordNext. 
                  }
                
              }
              neighborMap.put(keyWord, keyValue); //the key in the map will be the strings from training words named keyWord. The value in this position is an arrayList named keyValue. 
              //how do i extract the words from trainingWords and put the unique words into neighborMap
            }
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
   * 
   *  //[1][2][3][4][5] number qty size
      //[0][1][2][3][4] number index
   */
  public String predictNextWord(List<String> context) //list of Strings named context 
  {
    // TODO: Return a predicted word given the words preceding it
    String lastWord = context.get(context.size() -1);  //size will start with 1. index is 0. Get will want to get a number. 

   
    // Hint: only the last word in context should be looked at
    return null;
  }
  
  /**
   * Returns a copy of the neighbor map. The neighbor map is a mapping 
   * from each word to a list of words that have followed it in the training data.
   * 
   * You do not need to modify this method for your project.
   * 
   * @return a copy of the neighbor map
   */
  public Map<String, List<String>> getNeighborMap() 
  {
    Map<String, List<String>> copy = new HashMap<>();

    for (Map.Entry<String, List<String>> entry : neighborMap.entrySet()) 
    {
      List<String> newList = new ArrayList<>(entry.getValue());
      copy.put(entry.getKey(), newList);
    }

    return copy;
  }
}
