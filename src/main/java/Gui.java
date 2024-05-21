package wordPuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {

    private JFrame frame;
    private JPanel mainPanel;
    private Grid grid;
    private JComboBox<String> difficultyComboBox;

    public Gui() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        mainPanel = new JPanel(new BorderLayout());
        frame.getContentPane().add(mainPanel);

        showDifficultySelection();
        
        frame.setVisible(true);
    }

    private void showDifficultySelection() {
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setLayout(new GridLayout(2, 1));

        JLabel difficultyLabel = new JLabel("Select Difficulty:");
        difficultyPanel.add(difficultyLabel);

        String[] difficulties = {"Easy", "Medium", "Hard"};
        difficultyComboBox = new JComboBox<>(difficulties);
        difficultyPanel.add(difficultyComboBox);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                startGame(selectedDifficulty);
            }
        });

        mainPanel.add(difficultyPanel, BorderLayout.CENTER);
        mainPanel.add(startButton, BorderLayout.SOUTH);
    }

    private void startGame(String difficulty) {
        mainPanel.removeAll();
        
        // You might want to use the difficulty level to adjust grid parameters
        grid = new Grid(difficulty);
        mainPanel.add(grid.getGridPanel(), BorderLayout.CENTER);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Gui window = new Gui();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
