import java.util.*;

/**
 * Created by Chris on 2/9/17.
 * Anagrams class creates Anagrams object with print methods to display
 * the Anagrams in the dictionary with the given word/phrase
 */
public class Anagrams {
    private Map<String, LetterInventory> solver; // HashMap: keys of String and values of LetterInventory
    private List<String> dictionary; // the dictionary is a list of strings

    /**
     * Constructor initializes the dictionary and solver.
     * Solver is filled with the words from the dictionary and LetterInventories
     * @param dictionary is the list of words in the dictionary
     */
    public Anagrams(List<String> dictionary) {
        this.dictionary = dictionary;
        solver = new HashMap<String, LetterInventory>();
        for (String word : dictionary) {
            solver.put(word, new LetterInventory(word));
        }
    }

    /**
     * Calls its helper method, passing in the max, a Stack of Strings, and a LetterInventory to
     * Print the anagrams found
     * @param text the string that is passed in the helper for anagrams
     * @param max the max number of words
     * @throws IllegalArgumentException if the max is less than 0
     */
    public void print(String text, int max) throws IllegalArgumentException {
        if (max < 0)
            throw new IllegalArgumentException("Max cannot be less than zero.");
        print(max, new Stack<String>(), new LetterInventory(text));
    }

    /**
     * Helper method that uses recursive backtracking to print the anagrams
     * If the inventory is empty, then it would simply print an empty stack
     * If the stack size is greater than 0 or the max is equal to zero, then
     * it will run through a for each loop of the dictionary, create a copy of the
     * inventory and subtract the word from it. If the copy is not null, then it will
     * push the word in the stack, then recursively call print and then reach the last
     * line to remove the value at the top of the stack
     * @param max the max number of words
     * @param stack the Stack of strings that contains the answer (anagrams)
     * @param inventory is the LetterInventory passed in that will be duplicated
     */
    private void print(int max, Stack<String> stack, LetterInventory inventory) {
        if (inventory.isEmpty()) {
            System.out.println(stack);
        } else if (stack.size() < max || max == 0) {
            for (String word : this.dictionary) {
                LetterInventory copy = inventory.subtract(this.solver.get(word));
                if (copy != null){
                    stack.push(word);
                    print(max, stack, copy);
                    stack.pop();
                }
            }
        }
    }
}
