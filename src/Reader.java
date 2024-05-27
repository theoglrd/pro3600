package wordPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Reader {
    public static Set<String> loadFromCSV(String filePath) {
        Set<String> words = new HashSet<>();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] wordsArray = line.split(",");
                for (String word : wordsArray) {
                    words.add(word.trim().toUpperCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}