import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class UnigramWordPredictorTest {

    // Wave 4 - Testing the train method
    @Test
    void testTrainAndGetNeighborMap() {
        // Simulated training text with tokenized words
        FakeTokenizer fakeTokenizer = new FakeTokenizer(
            List.of("the", "cat", "sat", ".", "the", "cat", "slept", ".", "the", "dog", "barked", ".")
        );

        // Create predictor and train it
        UnigramWordPredictor predictor = new UnigramWordPredictor(fakeTokenizer);
        predictor.train(null); // Scanner input is ignored by FakeTokenizer

        // Get the generated neighbor map
        Map<String, List<String>> neighborMap = predictor.getNeighborMap();

        // Sort lists inside the map for easy comparison
        for (List<String> values : neighborMap.values()) {
            values.sort(null);
        }

        // Expected word mappings based on training text
        Map<String, List<String>> expectedMap = Map.of(
            "the", List.of("cat", "cat", "dog"),
            "cat", List.of("sat", "slept"),
            ".", List.of("the", "the"),
            "dog", List.of("barked"),
            "sat", List.of("."),
            "slept", List.of("."),
            "barked", List.of(".")
        );

        // Check if our model created the correct word map
        assertEquals(expectedMap, neighborMap);
    }

    // Wave 5 - Testing predictNextWord()
    @Test
    void testPredictNextWord() {
        // Simulated tokenized words for testing predictions
        FakeTokenizer fakeTokenizer = new FakeTokenizer(
            List.of("the", "quick", "brown", "fox", ".", "a", "quick", "red", "fox", ".", "the", "slow", "green", "turtle", ".")
        );

        // Create and train predictor
        UnigramWordPredictor predictor = new UnigramWordPredictor(fakeTokenizer);
        predictor.train(null);

        // Check word predictions based on training
        assertTrue(List.of("quick", "slow").contains(predictor.predictNextWord(List.of("the"))));
        assertEquals("quick", predictor.predictNextWord(List.of("a")));
        assertTrue(List.of("brown", "red").contains(predictor.predictNextWord(List.of("quick"))));
        assertEquals(".", predictor.predictNextWord(List.of("fox")));
        assertEquals("green", predictor.predictNextWord(List.of("slow")));
        assertEquals(".", predictor.predictNextWord(List.of("turtle")));
        assertTrue(List.of("the", "a").contains(predictor.predictNextWord(List.of("."))));
    }

    // Wave 5 - Probabilistic test for predictNextWord()
    @Test
    void testPredictNextWordProbabilistically() {
        // Training data
        FakeTokenizer fakeTokenizer = new FakeTokenizer(
            List.of("the", "cat", "sat", ".", "the", "cat", "slept", ".", "the", "dog", "barked", ".")
        );

        UnigramWordPredictor predictor = new UnigramWordPredictor(fakeTokenizer);
        predictor.train(null);

        // Number of trials for probability check
        int trials = 10000;
        double tolerance = 0.05; // Allow 5% deviation

        // Expected probabilities based on word frequency
        Map<String, Map<String, Double>> expectedProbabilities = Map.of(
            "the", Map.of("cat", 2.0 / 3.0, "dog", 1.0 / 3.0),
            "cat", Map.of("sat", 0.5, "slept", 0.5),
            "dog", Map.of("barked", 1.0),
            ".", Map.of("the", 1.0)
        );

        // Check predictions for each word
        for (String word : expectedProbabilities.keySet()) {
            Map<String, Integer> counts = new HashMap<>();
            Map<String, Double> expected = expectedProbabilities.get(word);

            // Initialize word count
            expected.keySet().forEach(nextWord -> counts.put(nextWord, 0));

            // Run trials to collect frequency data
            for (int i = 0; i < trials; i++) {
                String predictedWord = predictor.predictNextWord(List.of(word));
                counts.put(predictedWord, counts.getOrDefault(predictedWord, 0) + 1);
            }

            // Compare observed vs expected probability
            for (String nextWord : expected.keySet()) {
                double observedFrequency = counts.get(nextWord) / (double) trials;
                double expectedFrequency = expected.get(nextWord);
                assertTrue(Math.abs(observedFrequency - expectedFrequency) < tolerance,
                        "Frequency mismatch for '" + word + "': observed " + observedFrequency + ", expected " + expectedFrequency);
            }
        }
    }
}
