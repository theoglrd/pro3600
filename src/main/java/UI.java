package wordPuzzle;

import java.io.File;
import java.util.Scanner;

public class UI {
    private Scanner scanner = new Scanner(System.in);

    public String getUserInput() {
        return scanner.nextLine();
    }

    public int getUserInputAsInt() {
        System.out.print("Enter a number: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next(); // clear invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public void displayGrid(Grid grid) {
        for (char[] row : grid.getGrid()) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayScore(int score) {
        System.out.println("Score actuel: " + score);
    }

   public void viewDictionary(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));
        if (files != null && files.length > 0) {
            System.out.println("Available dictionaries:");
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("No CSV dictionaries found in the specified directory.");
        }
    }
}