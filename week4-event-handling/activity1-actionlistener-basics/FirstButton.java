// === FIRST INTERACTIVE BUTTON (EXPERIMENT VERSION) ===
// This version incorporates the Step 1.3 experiments: 
// custom messages, console logging, and a second button.
import java.awt.*;
import javax.swing.*;

public class FirstButton {
    public static void main(String[] args) {
        // CRITICAL: All Swing code must run on the EDT.
        SwingUtilities.invokeLater(FirstButton::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("First Interactive Button - Experiments");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 250);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));

        // 1. Create the first button (shows a custom popup message & title)
        JButton clickButton = new JButton("Click Me!");
        clickButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                "Hello! You successfully completed Experiment 1.3!", // Custom message
                "Custom Dialog Title", // Custom title
                JOptionPane.INFORMATION_MESSAGE);
        });

        // 2. Create the second button (prints to console instead of popup)[cite: 91]
        JButton consoleButton = new JButton("Print to Console");
        consoleButton.addActionListener(e -> {
            System.out.println("Clicked! Event logged to the console."); // Print to console[cite: 91]
        });

        // Add both buttons to the frame
        frame.add(clickButton);
        frame.add(consoleButton); // Add a second button[cite: 91]
        
        frame.setVisible(true);
    }
}