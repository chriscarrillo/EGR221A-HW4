import java.util.*;

/**
 * Created by Chris on 2/9/17.
 */
public class Anagrams {
    private Map<String, LetterInventory> solver;
    private List<String> dictionary;

    public Anagrams(List<String> dictionary) {
        this.dictionary = dictionary;
        solver = new HashMap<String, LetterInventory>();
        for (String word : dictionary) {
            solver.put(word, new LetterInventory(word));
        }
    }

    public void print(String text, int max) throws IllegalArgumentException {
        if (max < 0)
            throw new IllegalArgumentException("Max cannot be less than zero.");
        print(max, new Stack<String>(), new LetterInventory(text));
    }

    private void print(int max, Stack<String> stack, LetterInventory inventory) {
        if (inventory.isEmpty()) {
            System.out.println(stack);
        } else if (stack.size() < max || max == 0) {
            for(String word : this.dictionary){
                LetterInventory copy = inventory.subtract(this.solver.get(word));
                if(copy != null){
                    stack.push(word);
                    print(max, stack, copy);
                    stack.pop();
                }
            }
        }
    }
}
