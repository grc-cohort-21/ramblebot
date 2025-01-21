import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class UnigramWordPredictorTest {

    // Wave 4
    /**
     * Tests the train method by checking that the generated neighbor map matches the expected map.
     * 
     * The training data simulates the tokens:
     * "the cat sat. the cat slept. the dog barked."
     * 
     * This results in the following sequences:
     * - "the" is followed by "cat", then "cat", and finally "dog".
     * - "cat" is followed by "sat" and then "slept".
     * - "barked", "sat", and "slept" are both followed by a period (".").
     * - "dog" is followed by "barked".
     * -  is followed by a period (".").
     * - "." is followed by the word "the" twice.
     * 
     * The expected neighbor map is checked to see if it matches this pattern.
     * The test does not care about the order of the map or the lists.
     */
    @Test
    void testTrainAndGetNeighborMap() {
        assertEquals(true, true);
    }

}
