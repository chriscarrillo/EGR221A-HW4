import java.util.List;

/**
 * Created by Chris on 2/9/17.
 */
public class Anagrams {
    public Anagrams(List<String> dictionary) {

    }

    public void print(String text, int max) throws IllegalArgumentException {
        if (max < 0) {
            throw new IllegalArgumentException("Max cannot be less than zero.");
        }
    }
}
