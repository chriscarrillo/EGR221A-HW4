/**
 * Created by Chris on 2/7/17.
 */
public class LetterInventory {
    private static final int ALPHA_SIZE = 26; // Constant of the alphabet size. Used throughout the program

    private int[] inventory; // inventory of counters
    private char start = 'a'; // starts on the letter a
    private int size; // the size of the inventory

    // Constructor calls the constructor with a blank string passed in
    public LetterInventory() {
        this("");
    }

    /**
     * Constructs inventory of size ALPHA_SIZE. Sets data to the lowercase of data.
     * Initializes the size to 0. It counts the number of alphabetic characters, ignoring others
     * @param data is the string passed in
     */
    public LetterInventory(String data) {
        inventory = new int[ALPHA_SIZE];
        data = data.toLowerCase();
        size = 0;

        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) < (ALPHA_SIZE + start) && (data.charAt(i) >= start)) {
                inventory[data.charAt(i) - start]++;
                size++;
            }
        }
    }

    /**
     * Get returns the number of times the letter appears in the inventory
     * @param letter is the requested letter of the inventory
     * @return an int of the number of times the character appears in inventory
     * @throws IllegalArgumentException if the character passed in is not a letter
     */
    public int get(char letter) throws IllegalArgumentException {
        letter = Character.toLowerCase(letter);
        if (!Character.isLetter(letter))
            throw new IllegalArgumentException("The character is not a letter");
        return inventory[letter - start];
    }

    /**
     * Changes the count for the letter to the given value
     * @param letter is the letter
     * @param value is the value that is going to be set
     * @throws IllegalArgumentException if character is not a letter or if the value is negative
     */
    public void set(char letter, int value) throws IllegalArgumentException {
        if (!Character.isLetter(letter) || value < 0)
            throw new IllegalArgumentException("The character must be a letter and the value must be greater than 0");
        letter = Character.toLowerCase(letter);

        if (value == 0) {
            size -= inventory[letter - start];
            inventory[letter - start] = 0;
        } else {
            int diff = inventory[letter - start] - value;
            inventory[letter - start] = value;
            if (diff > 0)
                size -= diff;
            else
                size += Math.abs(diff);
        }
    }

    /**
     * Getter method to return the size
     * @return the size
     */
    public int size() {
        return size;
    }

    /**
     * Boolean that returns true/false if the size is 0 or not
     * @return true if size is 0; false if size is not 0
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * toString returns the inventory in a string representation
     * @return the inventory in string form
     */
    public String toString() {
        String o = "[";

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != 0) {
                for (int j = 1; j <= inventory[i]; j++) {
                    o += (char)(start + i);
                }
            }
        }

        o += "]";
        return o;
    }

    /**
     * Constructs a new LetterInventory that is the sum of the 2 LetterInventories
     * @param other LetterInventory to add
     * @return the added LetterInventory
     */
    public LetterInventory add(LetterInventory other) {
        LetterInventory together = new LetterInventory();
        for (int i = 0; i < inventory.length; i++) {
            together.inventory[i] = this.inventory[i] + other.inventory[i];
        }
        together.size = this.size + other.size;
        return together;
    }

    /**
     * Constructs a new LetterInventory that is the difference between the 2 LetterInventories
     * @param other LetterInventory to subtract
     * @return the LetterInventory subtracted
     */
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory diff = new LetterInventory();
        for (int i = 0; i < inventory.length; i++) {
            diff.inventory[i] = this.inventory[i] - other.inventory[i];
            if (diff.inventory[i] < 0)
                return null;
        }
        diff.size = this.size - other.size;
        return diff;
    }

    /**
     * Gets the percentage of the given letter in the inventory
     * @param letter to get the percentage of
     * @return the percentage of letters in the inventory that are the given letter
     * @throws IllegalArgumentException if the character is not alphabetic
     */
    public double getLetterPercentage(char letter) throws IllegalArgumentException {
        if (!Character.isLetter(letter))
            throw new IllegalArgumentException("The character is not a letter");
        letter = Character.toLowerCase(letter);
        if (size == 0)
            return 0.0;
        return (double) inventory[letter - start] / size;
    }
}