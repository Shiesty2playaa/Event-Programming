import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentTableDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Student Database - JTable");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 500);
            frame.setLocationRelativeTo(null);

            // ---- Column names ----
            String[] columns = {"ID", "Name", "Age", "Grade", "GPA"};

            // ---- Raw data ----
            Object[][] data = {
                {101, "Alice Johnson", 20, "A", 3.85},
                {102, "Bob Smith", 22, "B", 3.20},
                {103, "Charlie Brown", 19, "A-", 3.70},
                {104, "Diana Prince", 21, "B+", 3.40},
                {105, "Edward Norton", 23, "C", 2.50},
                {106, "Fiona Green", 20, "A", 3.95}
            };

            // ---- Create the Table Model ----
            DefaultTableModel model = new DefaultTableModel(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // Only allow editing Name and Grade columns
                    return column == 1 || column == 3;
                }
            };

            // ---- Create the JTable ----
            JTable table = new JTable(model);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Customize appearance 
            table.setRowHeight(25);
            table.setFont(new Font("Arial", Font.PLAIN, 14));
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

            // Custom renderer for the GPA column (index 4)
            table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if (!isSelected && value instanceof Double) {
                        double gpa = (Double) value;
                        if (gpa >= 3.5) {
                            c.setBackground(new Color(220, 255, 220)); // Soft green for high GPA
                        } else {
                            c.setBackground(new Color(255, 220, 220)); // Soft red for lower GPA
                        }
                    } else if (isSelected) {
                        c.setBackground(table.getSelectionBackground());
                    } else {
                        c.setBackground(table.getBackground());
                    }
                    return c;
                }
            });

            // ---- Buttons panel ----
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            JButton addButton = new JButton("Add Student");
            JButton deleteButton = new JButton("Delete Selected");
            JButton clearButton = new JButton("Clear All");

            // ---- Event Handlers ----

            // Add Student: opens a dialog to enter data 
            addButton.addActionListener(e -> {
                String name = JOptionPane.showInputDialog(frame, "Enter student name:");
                if (name == null || name.trim().isEmpty()) return;
                
                String ageStr = JOptionPane.showInputDialog(frame, "Enter age:");
                if (ageStr == null) return;
                
                String grade = JOptionPane.showInputDialog(frame, "Enter grade (i.e., A, B+):");
                if (grade == null) return;
                
                String gpaStr = JOptionPane.showInputDialog(frame, "Enter GPA:");
                if (gpaStr == null) return;

                try {
                    int newId = model.getRowCount() > 0 ? (int) model.getValueAt(model.getRowCount() - 1, 0) + 1 : 101;
                    int age = Integer.parseInt(ageStr.trim());
                    double gpa = Double.parseDouble(gpaStr.trim());
                    model.addRow(new Object[]{newId, name, age, grade, gpa});
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Age and GPA must be valid numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Delete selected
            deleteButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a student to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int confirm = JOptionPane.showConfirmDialog(frame, "Delete selected student?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    model.removeRow(selectedRow);
                }
            });

            // Clear all
            clearButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(frame, "Clear all student records?", "Confirm Clear", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    model.setRowCount(0);
                }
            });

            buttonPanel.add(addButton);
            buttonPanel.add(deleteButton);
            buttonPanel.add(clearButton);

            // ---- Layout ----
            frame.setLayout(new BorderLayout());
            frame.add(new JScrollPane(table), BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }
}