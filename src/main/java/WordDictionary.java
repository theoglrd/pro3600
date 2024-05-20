package wordPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Scanner;

public class WordDictionary {
	Scanner scanner = new Scanner(System.in);
    private Set<String> words = new HashSet<>();
    private String name;

    public WordDictionary(String name) {
    	System.out.println("Please name your Dictionary");
    	this.name = name;
    	this.words = new HashSet<>();
        this.scanner = new Scanner(System.in);
        addWords();
    }

    public WordDictionary(String name, Set<String> words) {
    	this.name = name;
    	this.words = new HashSet<>(words);
    }
    
    public String getName() {
        return this.name;
    }

    public void addWords() {
    	boolean bool;
    	bool=true;
    	while (bool) {
    		System.out.println("Please type your word or 'quit' to end the creation");
    		String a = this.scanner.nextLine();
    		
    		if (a.equals("quit")) {
    				bool=false;
    		}
    		else { 
    			this.words.add(a);
    				
    		}
    	
    	}
    	
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
