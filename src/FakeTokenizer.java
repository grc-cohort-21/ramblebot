import java.util.List;
import java.util.Scanner;

/**
 * A simple fake tokenizer that returns a predefined list of tokens.  
 * Finish project on time
 * This is useful for testing the UnigramWordPredictor in isolation.
 * 
 * To learn more about fakes in testing, read this (optional):
 * https://www.petrikainulainen.net/programming/testing/introduction-to-fakes
 */
class FakeTokenizer implements Tokenizer {
    private final List<String> tokens;

    /**
     * Constructs a FakeTokenizer with the specified list of tokens.
     * 
     * @param tokens the predefined list of tokens to return
     */
    public FakeTokenizer(List<String> tokens) {
        this.tokens = tokens;
    }

    @Override
    public List<String> tokenize(Scanner scanner) {
        // Ignore the scanner input and return the predefined list of tokens
        return tokens;
    }
}
