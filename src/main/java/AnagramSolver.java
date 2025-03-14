import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AnagramSolver {

    /**
     * Builds a HashMap where keys are sorted versions of words and values are lists of anagrams.
     * @param filename Name of the file containing English words.
     * @return A HashMap holding lists of words that are anagrams.
     */
    public static HashMap<String, ArrayList<String>> anagrams(String filename) {
        HashMap<String, ArrayList<String>> anagramMap = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim().toLowerCase();
                // Sort characters in the word to create a key for anagrams
                char[] charArray = word.toCharArray();
                Arrays.sort(charArray);
                String sortedWord = new String(charArray);

                // Add word to the appropriate list in the map
                anagramMap.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        return anagramMap;
    }

    /**
     * Finds the list with the most words in the given HashMap of anagrams.
     * @param anagrams HashMap containing lists of anagrams.
     * @return The list with the most words.
     */
    public static ArrayList<String> mostFrequentAnagram(HashMap<String, ArrayList<String>> anagrams) {
        ArrayList<String> mostFrequent = new ArrayList<>();
        int maxCount = 0;

        for (ArrayList<String> list : anagrams.values()) {
            if (list.size() > maxCount) {
                maxCount = list.size();
                mostFrequent = list;
            }
        }

        return mostFrequent;
    }

    /**
     * Prints all key-value pairs in the given HashMap of anagrams.
     * @param anagrams HashMap containing lists of anagrams.
     */
    public static void printKeyValuePairs(HashMap<String, ArrayList<String>> anagrams) {
        for (Map.Entry<String, ArrayList<String>> entry : anagrams.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        String filename = "words.txt"; // Replace with your file name
        HashMap<String, ArrayList<String>> anagramMap = anagrams(filename);
        printKeyValuePairs(anagramMap);
        ArrayList<String> mostFrequent = mostFrequentAnagram(anagramMap);
        System.out.println("Most Frequent Anagrams: " + mostFrequent);
    }
}
