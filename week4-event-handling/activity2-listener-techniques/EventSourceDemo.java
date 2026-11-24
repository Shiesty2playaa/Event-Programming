import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EventSourceDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { //[cite: 14]
            JFrame frame = new JFrame("Finding the Event Source"); //[cite: 14]
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //[cite: 14]
            frame.setSize(500, 300); //[cite: 14]
            frame.setLocationRelativeTo(null); //[cite: 14]
            frame.setLayout(new GridLayout(4, 1, 10, 10)); //[cite: 14]

            JLabel resultLabel = new JLabel("Click a button...", SwingConstants.CENTER); //[cite: 14]
            resultLabel.setFont(new Font("Arial", Font.BOLD, 16)); //[cite: 14]

            JButton addButton = new JButton("Add"); //[cite: 14]
            JButton subtractButton = new JButton("Subtract"); //[cite: 14]
            JButton resetButton = new JButton("Reset"); //[cite: 14]

            ActionListener sharedListener = e -> { //[cite: 14]
                Object source = e.getSource(); //[cite: 14]
                String command = e.getActionCommand(); //[cite: 14]

                if (source == addButton) { //[cite: 14]
                    resultLabel.setText("You clicked: ADD"); //[cite: 14]
                } else if (source == subtractButton) { //[cite: 15]
                    resultLabel.setText("You clicked: SUBTRACT"); //[cite: 15]
                } else if (source == resetButton) { //[cite: 15]
                    resultLabel.setText("You clicked: RESET"); //[cite: 15]
                }
                System.out.println("Command: " + command); //[cite: 15]
            };

            addButton.addActionListener(sharedListener); //[cite: 15]
            subtractButton.addActionListener(sharedListener); //[cite: 15]
            resetButton.addActionListener(sharedListener); //[cite: 15]

            frame.add(addButton); //[cite: 15]
            frame.add(subtractButton); //[cite: 15]
            frame.add(resetButton); //[cite: 15]
            frame.add(resultLabel); //[cite: 15]
            frame.setVisible(true); //[cite: 15]
        });
    }
}