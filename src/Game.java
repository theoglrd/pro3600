package wordPuzzle;

/*public class Game {
    private Grid grid;
    private WordDictionary dictionary;
    private UI ui = new UI();
    private boolean isGameOver;
    private int score;
    private int difficulty;
    private Set<String> wordsContained;
    private Set<String> foundWords;

    public Game(int width, int height) {
        grid = new Grid(width, height);
        isGameOver = false;
        score = 0;
        difficulty=selectDifficulty();
        dictionary=selectSubject();
        wordsContained=prepareNewGame();
        foundWords = new HashSet<>();
    }

    public Grid getGrid() {
        return this.grid;
    }
    public int getScord() {
        return this.score;
    }

    public int selectDifficulty() {
        System.out.println("Please select a game difficulty from 0 to 5");
        //Scanner scanner = new Scanner(System.in);
        //int difficulty = Gui.graphicDifficulty();
        int difficulty = ui.getUserInputAsInt();
        if (difficulty < 0 || difficulty > 5){
            throw new IllegalArgumentException("Invalid selection");
        }
        System.out.println("Difficulty selected"); // Debugging message
        return difficulty;
    }

    public WordDictionary selectSubject() {
        ui.viewDictionary(".\\src\\main\\resources\\");
        System.out.println("Please select an existing subject (use viewDictionary to view available subjects)");
        //Scanner scanner = new Scanner(System.in);
        //String dictionaryName = scanner.nextLine(); // Read the input here
        String dictionaryName = ui.getUserInput();
        System.out.println("You selected: " + dictionaryName); // Debugging message
        
        // Construct the file path
        String filePath = ".\\src\\main\\resources\\" + dictionaryName + ".csv";
        System.out.println("Loading dictionary from: " + filePath); // Debugging message
        
        // Load dictionary from the file
        Set<String> words = Reader.loadFromCSV(filePath);
        
        if (words.isEmpty()) {
            System.out.println("No words found in the dictionary."); // Debugging message
        } else {
            System.out.println("Dictionary loaded with " + words.size() + " words."); // Debugging message
        }
        
        return new WordDictionary(dictionaryName, words);
    }

    private Set<String> prepareNewGame() {
        System.out.println("Preparing new game..."); // Debugging message
        grid.initializeGrid();
        System.out.println("Grid initialized."); // Debugging message
        Set<String> words = dictionary.wordPool(grid);
        System.out.println("Words selected from dictionary."); // Debugging message
        Random rand = new Random();
        for (String word : words) {
            int startX = rand.nextInt(grid.getHeight());
            int startY = rand.nextInt(grid.getWidth());
            Word wordObj = new Word(word, startX, startY, Word.Orientation.DIAGONALrightdown);
            grid.brutForce(wordObj);
            }
        System.out.println("Words placed."); // Debugging message
        for (int i = 0; i <= difficulty; i++) {
            grid.randomRotate();
        }
        System.out.println("Grid rotated."); // Debugging message
        grid.fillEmptySpaces();
        ui.displayGrid(grid);
        return words;
    }
    
    public void start() {
        while (!isGameOver) {
            ui.displayMessage("Enter a word, Rotate the grid, or 'quit' to quit!");
            String userInput = ui.getUserInput();
            userInput = userInput.toUpperCase();

            if (userInput.equalsIgnoreCase("quit")) {
                isGameOver = true;
                continue;
            }

            switch (userInput.toUpperCase()) {
                case "ROTATE COLUMN UP":
                    ui.displayMessage("Enter the column index to rotate up:");
                    int colIndexUp = ui.getUserInputAsInt();
                    if (colIndexUp >= 0 && colIndexUp < grid.getWidth()) {
                        grid.rotateColumnUp(colIndexUp);
                    } else {
                        ui.displayMessage("Invalid column index.");
                    }
                    break;

                case "ROTATE COLUMN DOWN":
                    ui.displayMessage("Enter the column index to rotate down:");
                    int colIndexDown = ui.getUserInputAsInt();
                    if (colIndexDown >= 0 && colIndexDown < grid.getWidth()) {
                        grid.rotateColumnDown(colIndexDown);
                    } else {
                        ui.displayMessage("Invalid column index.");
                    }
                    break;

                case "ROTATE ROW LEFT":
                    ui.displayMessage("Enter the row index to rotate left:");
                    int rowIndexLeft = ui.getUserInputAsInt();
                    if (rowIndexLeft >= 0 && rowIndexLeft < grid.getHeight()) {
                        grid.rotateRowLeft(rowIndexLeft);
                    } else {
                        ui.displayMessage("Invalid row index.");
                    }
                    break;

                case "ROTATE ROW RIGHT":
                    ui.displayMessage("Enter the row index to rotate right:");
                    int rowIndexRight = ui.getUserInputAsInt();
                    if (rowIndexRight >= 0 && rowIndexRight < grid.getHeight()) {
                        grid.rotateRowRight(rowIndexRight);
                    } else {
                        ui.displayMessage("Invalid row index.");
                    }
                    break;

                default:
                    if (!foundWords.contains(userInput)) {
                        if (wordsContained.contains(userInput)) {
                            score += userInput.length(); // Points based on word length
                            foundWords.add(userInput);
                            ui.displayMessage("Correct! Your score: " + score);
                        } else {
                            ui.displayMessage("Word not in the grid.");
                        }
                    } else {
                        ui.displayMessage("Word already found.");
                }
            }
            ui.displayGrid(grid); // Actualize grid after each actions
        }
        ui.displayMessage("Game over! Your final score: " + score);
    }
}*/

import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {
    private Grid grid;
    private WordDictionary dictionary;
    private UI ui = new UI();
    private boolean isGameOver;
    private int score;
    private int difficulty;
    private Set<String> wordsContained;
    private Set<String> foundWords;

    public Game(int width, int height, String dictionaryName, int difficulty) {
        grid = new Grid(width, height);
        isGameOver = false;
        this.score = 0;
        this.difficulty = difficulty;
        this.dictionary = loadDictionary(dictionaryName);
        this.wordsContained = prepareNewGame();
        this.foundWords = new HashSet<>();
    }

    private WordDictionary loadDictionary(String dictionaryName) {
        String filePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + dictionaryName + ".csv";
        Set<String> words = Reader.loadFromCSV(filePath);
        if (words.isEmpty()) {
            throw new IllegalArgumentException("No words found in the dictionary.");
        }
        return new WordDictionary(dictionaryName, words);
    }

    private Set<String> prepareNewGame() {
        grid.initializeGrid();
        Set<String> words = dictionary.wordPool(grid);
        Random rand = new Random();
        for (String word : words) {
            int startX = rand.nextInt(grid.getHeight());
            int startY = rand.nextInt(grid.getWidth());
            Word wordObj = new Word(word, startX, startY, Word.Orientation.DIAGONALrightdown);
            grid.brutForce(wordObj);
        }
        for (int i = 0; i <= difficulty; i++) {
            grid.randomRotate();
        }
        grid.fillEmptySpaces();
        ui.displayGrid(grid);
        return words;
    }

    public boolean checkWord(String userInput) {
        if (!foundWords.contains(userInput)) {
            if (wordsContained.contains(userInput)) {
                score += userInput.length();
                foundWords.add(userInput);
                return true;
            }
        }
        return false;
    }

    public int getScore() {
        return this.score;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void rotateColumnUp(int colIndex) {
        grid.rotateColumnUp(colIndex);
    }

    public void rotateColumnDown(int colIndex) {
        grid.rotateColumnDown(colIndex);
    }

    public void rotateRowLeft(int rowIndex) {
        grid.rotateRowLeft(rowIndex);
    }

    public void rotateRowRight(int rowIndex) {
        grid.rotateRowRight(rowIndex);
    }
}