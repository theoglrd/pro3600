package wordPuzzle;

import java.util.HashSet;
import java.util.Set;

public class WordDictionary {
    private Set<String> words;
    private String name;

    public WordDictionary(String name, Set<String> words) {
        this.name = name;
        this.words = new HashSet<>(words);
    }

    public String getName() {
        return this.name;
    }

    public Set<String> wordPool(Grid grid) {
        Set<String> list = new HashSet<>();
        int height = grid.getHeight();
        int width = grid.getWidth();
        double threshold = height * width / 2.7;
        int lettercount = 0;
        boolean bool = true;
        for (String word : words) {
            if (lettercount > threshold) {
                bool = false;
            }
            if (bool && word.length() <= 10) {
                list.add(word);
                lettercount += word.length();
            }
        }
        if (list.size() < 1) {
            throw new IllegalArgumentException("Grid too small to place words");
        }
        return list;
    }
}
/*
package wordPuzzle;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class WordDictionary {
    private Set<String> words = new HashSet<>();
    private String name;

    public WordDictionary(String name, Set<String> words) {
        this.name = name;
        this.words = new HashSet<>(words);
    }

    public String getName() {
        return this.name;
    }

    public Set<String> wordPool(Grid grid) {
        Set<String> list = new HashSet<>();
        int height = grid.getHeight();
        int width = grid.getWidth();
        double threshold = height * width / 2.7;
        int lettercount = 0;
        boolean bool = true;
        List<String> remainingWords = new ArrayList<>(words);
        Random random = new Random();
        while (bool && !remainingWords.isEmpty()) {
            int index = random.nextInt(remainingWords.size());
            String word = remainingWords.remove(index);
            lettercount += word.length();

            if (!isWordValid(word) || lettercount > threshold) {
                bool = false;
            } else {
                list.add(word);
            }
        }
        if (list.size() < 1) {
            throw new IllegalArgumentException("Grid too small to place words");
        }
        return list;
    }

    public String getRandomWord() {
        Random random = new Random();
        int index = random.nextInt(this.words.size());
        return (String) words.toArray()[index];
    }

    public boolean isWordValid(String word) {
        return words.contains(word.toUpperCase());
    }
}
*/