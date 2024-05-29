/*package wordPuzzle;

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
*/
package wordPuzzle;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String> listCSVFiles(String directoryPath) {
        List<String> csvFiles = new ArrayList<>();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));
        if (files != null) {
            for (File file : files) {
                csvFiles.add(file.getName().replace(".csv", ""));
            }
        }
        return csvFiles;
    }
}