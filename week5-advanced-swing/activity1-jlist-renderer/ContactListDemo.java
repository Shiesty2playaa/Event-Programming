import java.awt.*;
import javax.swing.*;

public class ContactListDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contact List - Custom Renderer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout(10, 10));

            // Data - a simple list of contacts
            String[] contacts = {
                "Alice - Online", 
                "Jane - Offline", 
                "Johnson - Online", 
                "Bob - Away", 
                "Charlie - Online", 
                "David - Offline", 
                "Eve - Away"
            };

            // Create a JList with the contact data
            JList<String> contactList = new JList<>(contacts);
            contactList.setFont(new Font("Arial", Font.PLAIN, 14));

            // Create reusable icon instances
            Icon greenIcon = new ColorIcon(Color.GREEN);
            Icon redIcon = new ColorIcon(Color.RED);
            Icon orangeIcon = new ColorIcon(Color.ORANGE);
            Icon grayIcon = new ColorIcon(Color.GRAY);

            // Set a custom cell renderer for the JList
            contactList.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    // Let the default renderer handle the basic rendering
                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    String text = value.toString();

                    // Change the icon based on the contact's status
                    if (text.contains("Online")) {
                        label.setIcon(greenIcon);
                    } else if (text.contains("Offline")) {
                        label.setIcon(redIcon);
                    } else if (text.contains("Away")) {
                        label.setIcon(orangeIcon);
                    } else {
                        label.setIcon(grayIcon);
                    }

                    label.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
                    return label;
                }
            });

            // Add a selection listener for feedback
            JTextArea infoArea = new JTextArea(3, 30);
            infoArea.setEditable(false);
            
            contactList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    infoArea.setText("Selected: " + contactList.getSelectedValue()); 
                }
            });   

            frame.add(new JScrollPane(contactList), BorderLayout.CENTER);
            frame.add(new JScrollPane(infoArea), BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }

    // Simple icon class that draws a colored circle
    static class ColorIcon implements Icon {
        private final Color color;
        
        public ColorIcon(Color color) {
            this.color = color;
        }

        @Override 
        public void paintIcon(Component c, Graphics g, int x, int y) {
           
            g.setColor(color);
            g.fillOval(x, y + 2, 12, 12); // Vertically centered
            
        }

        @Override 
        public int getIconWidth() {
            return 14;
        }

        @Override 
        public int getIconHeight() {
            return 14;
        }
    }
}