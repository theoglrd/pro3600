package wordPuzzle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordPuzzleGUI {
    private JFrame frame;
    private JPanel gridPanel;
    private JTextField wordInput;
    private JLabel messageLabel;
    private JLabel scoreLabel;
    private Game game;

    public WordPuzzleGUI() {
        frame = new JFrame("Word Puzzle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Top Panel for dictionary and difficulty selection
        JPanel topPanel = new JPanel(new FlowLayout());
        String[] dictionaries = {"fruits"}; // Example dictionaries, ensure these match your actual files
        JComboBox<String> dictionaryComboBox = new JComboBox<>(dictionaries);
        topPanel.add(new JLabel("Select Dictionary:"));
        topPanel.add(dictionaryComboBox);

        String[] difficulties = {"0", "1", "2", "3", "4", "5"};
        JComboBox<String> difficultyComboBox = new JComboBox<>(difficulties);
        topPanel.add(new JLabel("Select Difficulty:"));
        topPanel.add(difficultyComboBox);

        JButton startButton = new JButton("Start Game");
        topPanel.add(startButton);

        JLabel titleLabel = new JLabel("Word Puzzle Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLUE);
        topPanel.add(titleLabel, BorderLayout.NORTH);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        topPanel.setBorder(blackline);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Grid Panel for displaying the puzzle
        JPanel gridWrapperPanel = new JPanel(new BorderLayout());
        gridPanel = new JPanel();
        gridWrapperPanel.add(gridPanel, BorderLayout.CENTER);

        // Top and bottom rotation panels
        JPanel topRotatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JPanel bottomRotatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        for (int i = 0; i < 10; i++) {
            int colIndex = i;
            JButton rotateUpButton = new JButton("↑");
            rotateUpButton.addActionListener(e -> {
                game.rotateColumnUp(colIndex);
                updateGrid();
            });
            topRotatePanel.add(rotateUpButton);

            JButton rotateDownButton = new JButton("↓");
            rotateDownButton.addActionListener(e -> {
                game.rotateColumnDown(colIndex);
                updateGrid();
            });
            bottomRotatePanel.add(rotateDownButton);
        }

        // Left and right rotation panels
        JPanel leftRotatePanel = new JPanel(new GridLayout(10, 1, 5, 5));
        JPanel rightRotatePanel = new JPanel(new GridLayout(10, 1, 5, 5));

        for (int i = 0; i < 10; i++) {
            int rowIndex = i;
            JButton rotateLeftButton = new JButton("←");
            rotateLeftButton.addActionListener(e -> {
                game.rotateRowLeft(rowIndex);
                updateGrid();
            });
            leftRotatePanel.add(rotateLeftButton);

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

        mainPanel.add(gridWrapperPanel, BorderLayout.CENTER);

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

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Action Listeners for buttons
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDictionary = (String) dictionaryComboBox.getSelectedItem();
                int selectedDifficulty = Integer.parseInt((String) difficultyComboBox.getSelectedItem());
                game = new Game(10, 10, selectedDictionary, selectedDifficulty);
                updateGrid();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = wordInput.getText().toUpperCase();
                if (game.checkWord(word)) {
                    messageLabel.setText("Correct! Word found.");
                    scoreLabel.setText("Score: " + game.getScore());
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
        gridPanel.setLayout(new GridLayout(game.getGrid().getHeight(), game.getGrid().getWidth()));
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

    private int getColumnIndex() {
        String input = JOptionPane.showInputDialog(frame, "Enter column index:");
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid column index.");
            return -1;
        }
    }

    private int getRowIndex() {
        String input = JOptionPane.showInputDialog(frame, "Enter row index:");
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid row index.");
            return -1;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WordPuzzleGUI());
    }
}
/*
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordPuzzleGUI {
    private JFrame frame;
    private JPanel gridPanel;
    private JTextField wordInput;
    private JLabel messageLabel;
    private JLabel scoreLabel;
    private Game game;

    public WordPuzzleGUI() {
        frame = new JFrame("Word Puzzle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Top Panel for dictionary and difficulty selection
        JPanel topPanel = new JPanel(new FlowLayout());
        String[] dictionaries = {"fruits"}; // Example dictionaries, ensure these match your actual files
        JComboBox<String> dictionaryComboBox = new JComboBox<>(dictionaries);
        topPanel.add(new JLabel("Select Dictionary:"));
        topPanel.add(dictionaryComboBox);

        String[] difficulties = {"0", "1", "2", "3", "4", "5"};
        JComboBox<String> difficultyComboBox = new JComboBox<>(difficulties);
        topPanel.add(new JLabel("Select Difficulty:"));
        topPanel.add(difficultyComboBox);

        JButton startButton = new JButton("Start Game");
        topPanel.add(startButton);

        JLabel titleLabel = new JLabel("Word Puzzle Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLUE);
        topPanel.add(titleLabel, BorderLayout.NORTH);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        topPanel.setBorder(blackline);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Grid Panel for displaying the puzzle
        gridPanel = new JPanel();
        gridPanel.setBorder(blackline);
        mainPanel.add(gridPanel, BorderLayout.CENTER);

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

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Rotation Buttons Panel
        JPanel rotationPanel = new JPanel();
        rotationPanel.setLayout(new GridLayout(2, 2, 10, 10)); // add spacing
        rotationPanel.setBorder(blackline);

        JButton rotateColUpButton = new JButton("Rotate Column Up");
        JButton rotateColDownButton = new JButton("Rotate Column Down");
        JButton rotateRowLeftButton = new JButton("Rotate Row Left");
        JButton rotateRowRightButton = new JButton("Rotate Row Right");

        rotationPanel.add(rotateColUpButton);
        rotationPanel.add(rotateColDownButton);
        rotationPanel.add(rotateRowLeftButton);
        rotationPanel.add(rotateRowRightButton);

        mainPanel.add(rotationPanel, BorderLayout.EAST);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Action Listeners for buttons
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDictionary = (String) dictionaryComboBox.getSelectedItem();
                int selectedDifficulty = Integer.parseInt((String) difficultyComboBox.getSelectedItem());
                game = new Game(10, 10, selectedDictionary, selectedDifficulty);
                updateGrid();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = wordInput.getText().toUpperCase();
                if (game.checkWord(word)) {
                    messageLabel.setText("Correct! Word found.");
                    scoreLabel.setText("Score: " + game.getScore());
                } else {
                    messageLabel.setText("Word not found or already found.");
                }
                wordInput.setText("");
                updateGrid();
            }
        });

        rotateColUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int colIndex = getColumnIndex();
                if (colIndex >= 0) {
                    game.rotateColumnUp(colIndex);
                    updateGrid();
                }
            }
        });

        rotateColDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int colIndex = getColumnIndex();
                if (colIndex >= 0) {
                    game.rotateColumnDown(colIndex);
                    updateGrid();
                }
            }
        });

        rotateRowLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = getRowIndex();
                if (rowIndex >= 0) {
                    game.rotateRowLeft(rowIndex);
                    updateGrid();
                }
            }
        });

        rotateRowRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = getRowIndex();
                if (rowIndex >= 0) {
                    game.rotateRowRight(rowIndex);
                    updateGrid();
                }
            }
        });
    }

    private void updateGrid() {
        gridPanel.removeAll();
        gridPanel.setLayout(new GridLayout(game.getGrid().getHeight(), game.getGrid().getWidth()));
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

    private int getColumnIndex() {
        String input = JOptionPane.showInputDialog(frame, "Enter column index:");
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid column index.");
            return -1;
        }
    }

    private int getRowIndex() {
        String input = JOptionPane.showInputDialog(frame, "Enter row index:");
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid row index.");
            return -1;
        }
    }
}
*/