package wordPuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    private static int selectedButtonNumber = -1;
    private static JDialog dialog;

    public static int graphicDifficulty() {
        // Create the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });

        // Wait until a button is clicked
        while (selectedButtonNumber == -1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return selectedButtonNumber;
    }

    private static void createAndShowGUI() {
        // Create a new frame
        JFrame frame = new JFrame("Questionnaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        // Create a panel for the question
        JPanel questionPanel = new JPanel();
        JLabel questionLabel = new JLabel("Please select a difficulty level:");
        questionPanel.add(questionLabel);
        frame.add(questionPanel, BorderLayout.NORTH);

        // Create a panel with a grid layout for the answers
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Create buttons for the answers
        String[] answers = {"0", "1", "2", "3", "4", "5"};
        for (int i = 0; i < answers.length; i++) {
            JButton button = new JButton(answers[i]);
            int buttonNumber = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedButtonNumber = buttonNumber;
                    JOptionPane.showMessageDialog(frame, "You clicked button number: " + buttonNumber);
                    dialog.dispose();  // Close the dialog
                }
            });
            panel.add(button);
        }

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Create and set up the dialog
        dialog = new JDialog(frame, "Select Difficulty", true);
        dialog.getContentPane().add(frame.getContentPane());
        dialog.setSize(400, 400);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}
