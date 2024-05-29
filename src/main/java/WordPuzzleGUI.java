package wordPuzzle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WordPuzzleGUI {
    private JFrame frame;
    private JPanel startPanel;
    private JPanel gamePanel;
    private JPanel gridPanel;
    private JTextField wordInput;
    private JLabel messageLabel;
    private JLabel scoreLabel;
    private Game game;

    public WordPuzzleGUI() {
        frame = new JFrame("Word Puzzle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        // Create start panel with background image
        startPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("src/main/resources/background.jpg"); // Change to your image path
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Word Puzzle Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        startPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1; // Reset gridwidth
        JLabel selectDictionaryLabel = new JLabel("Select Dictionary:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        startPanel.add(selectDictionaryLabel, gbc);

        List<String> dictionaries = Reader.listCSVFiles("src/main/resources"); // Get the list of dictionaries
        JComboBox<String> dictionaryComboBox = new JComboBox<>(dictionaries.toArray(new String[0]));
        gbc.gridx = 1;
        gbc.gridy = 1;
        startPanel.add(dictionaryComboBox, gbc);

        JLabel selectDifficultyLabel = new JLabel("Select Difficulty:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        startPanel.add(selectDifficultyLabel, gbc);

        String[] difficulties = {"0", "1", "2", "3", "4", "5"};
        JComboBox<String> difficultyComboBox = new JComboBox<>(difficulties);
        gbc.gridx = 1;
        gbc.gridy = 2;
        startPanel.add(difficultyComboBox, gbc);

        JButton startButton = new JButton("Start Game");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        startPanel.add(startButton, gbc);

        frame.add(startPanel);
        frame.setVisible(true);

        // Create game panel
        gamePanel = new JPanel(new BorderLayout());

        // Grid Panel for displaying the puzzle
        JPanel gridWrapperPanel = new JPanel(new BorderLayout());
        gridPanel = new JPanel(new GridLayout(10, 10));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        gridPanel.setBorder(blackline);

        // Add top rotate buttons
        JPanel topRotatePanel = new JPanel(new GridLayout(1, 10));
        for (int i = 0; i < 10; i++) {
            int colIndex = i;
            JButton rotateUpButton = new JButton("↑");
            rotateUpButton.addActionListener(e -> {
                game.rotateColumnUp(colIndex);
                updateGrid();
            });
            topRotatePanel.add(rotateUpButton);
        }

        // Add bottom rotate buttons
        JPanel bottomRotatePanel = new JPanel(new GridLayout(1, 10));
        for (int i = 0; i < 10; i++) {
            int colIndex = i;
            JButton rotateDownButton = new JButton("↓");
            rotateDownButton.addActionListener(e -> {
                game.rotateColumnDown(colIndex);
                updateGrid();
            });
            bottomRotatePanel.add(rotateDownButton);
        }

        // Add left rotate buttons
        JPanel leftRotatePanel = new JPanel(new GridLayout(10, 1));
        for (int i = 0; i < 10; i++) {
            int rowIndex = i;
            JButton rotateLeftButton = new JButton("←");
            rotateLeftButton.addActionListener(e -> {
                game.rotateRowLeft(rowIndex);
                updateGrid();
            });
            leftRotatePanel.add(rotateLeftButton);
        }

        // Add right rotate buttons
        JPanel rightRotatePanel = new JPanel(new GridLayout(10, 1));
        for (int i = 0; i < 10; i++) {
            int rowIndex = i;
            JButton rotateRightButton = new JButton("→");
            rotateRightButton.addActionListener(e -> {
                game.rotateRowRight(rowIndex);
                updateGrid();
            });
            rightRotatePanel.add(rotateRightButton);
        }

        gridWrapperPanel.add(topRotatePanel, BorderLayout.NORTH);
        gridWrapperPanel.add(bottomRotatePanel, BorderLayout.SOUTH);
        gridWrapperPanel.add(leftRotatePanel, BorderLayout.WEST);
        gridWrapperPanel.add(rightRotatePanel, BorderLayout.EAST);
        gridWrapperPanel.add(gridPanel, BorderLayout.CENTER);

        gamePanel.add(gridWrapperPanel, BorderLayout.CENTER);

        // Bottom Panel for user input and messages
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(blackline);

        wordInput = new JTextField();
        bottomPanel.add(wordInput, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit Word");
        bottomPanel.add(submitButton, BorderLayout.EAST);

        messageLabel = new JLabel("Welcome to Word Puzzle!");
        bottomPanel.add(messageLabel, BorderLayout.NORTH);

        scoreLabel = new JLabel("Score: 0");
        bottomPanel.add(scoreLabel, BorderLayout.SOUTH);

        gamePanel.add(bottomPanel, BorderLayout.SOUTH);

        // Action Listener for Start Game button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDictionary = (String) dictionaryComboBox.getSelectedItem();
                int selectedDifficulty = Integer.parseInt((String) difficultyComboBox.getSelectedItem());
                game = new Game(10, 10, selectedDictionary, selectedDifficulty);
                updateGrid();
                messageLabel.setText("Game started! Find the words.");
                frame.remove(startPanel);
                frame.add(gamePanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        // Action Listener for Submit Word button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = wordInput.getText().toUpperCase();
                if (game.checkWord(word)) {
                    messageLabel.setText("Correct! Word found.");
                    scoreLabel.setText("Score: " + game.getScore());
                    if (game.isGameOver()) {
                        messageLabel.setText("You win! No more words.");
                    }
                } else {
                    messageLabel.setText("Word not found or already found.");
                }
                wordInput.setText("");
                updateGrid();
            }
        });
    }

    private void updateGrid() {
        gridPanel.removeAll();
        char[][] gridData = game.getGrid().getGrid();
        for (char[] row : gridData) {
            for (char cell : row) {
                JLabel cellLabel = new JLabel(String.valueOf(cell), SwingConstants.CENTER);
                cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cellLabel.setFont(new Font("Arial", Font.BOLD, 18));
                gridPanel.add(cellLabel);
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WordPuzzleGUI());
    }
}