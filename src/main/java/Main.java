/*package wordPuzzle;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Création du dictionnaire de fruits
        Set<String> fruits = new HashSet<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");
        fruits.add("Elderberry");
        fruits.add("Fig");
        fruits.add("Grape");
        fruits.add("Honeydew");
        fruits.add("Kiwi");
        fruits.add("Lemon");
        fruits.add("Mango");
        fruits.add("Nectarine");
        fruits.add("Orange");
        fruits.add("Papaya");
        fruits.add("Quince");
        fruits.add("Raspberry");
        fruits.add("Strawberry");
        fruits.add("Tomato");
        fruits.add("Ugli fruit");
        fruits.add("Watermelon");

        // Chemin du fichier CSV
        String filePath = ".\\src\\main\\resources\\fruits.csv";

        // Écrire les fruits dans le fichier CSV
        Writer.writeToCSV(filePath, fruits);
        System.out.println("Le dictionnaire de fruits a été écrit dans le fichier CSV.");

        // Lire les fruits depuis le fichier CSV
        Set<String> loadedFruits = Reader.loadFromCSV(filePath);

        // Afficher les mots du dictionnaire de fruits
        System.out.println("Mots dans le dictionnaire de fruits :");
        for (String fruit : loadedFruits) {
            System.out.println(fruit);
        }
        
        // Créer et lancer le jeu
        Game game = new Game(10, 10); // Initialisation du jeu avec une grille de 10x10
        game.start();
    }
}
*/
package wordPuzzle;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Création du dictionnaire de fruits
        Set<String> fruits = new HashSet<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");
        fruits.add("Elderberry");
        fruits.add("Fig");
        fruits.add("Grape");
        fruits.add("Honeydew");
        fruits.add("Kiwi");
        fruits.add("Lemon");
        fruits.add("Mango");
        fruits.add("Nectarine");
        fruits.add("Orange");
        fruits.add("Papaya");
        fruits.add("Quince");
        fruits.add("Raspberry");
        fruits.add("Strawberry");
        fruits.add("Tomato");
        fruits.add("Watermelon");

        // Chemin du fichier CSV
        String filePath = "src/main/resources/fruits.csv";

        // Écrire les fruits dans le fichier CSV
        Writer.writeToCSV(filePath, fruits);
        System.out.println("Le dictionnaire de fruits a été écrit dans le fichier CSV.");

        // Lancer l'application GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WordPuzzleGUI();
            }
        });
    }
}