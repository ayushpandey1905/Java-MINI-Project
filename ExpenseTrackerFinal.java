import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.SimpleDateFormat;

class Expense {
    String category;
    double amount;
    String date;

    Expense(String category, double amount, String date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }
}

public class ExpenseTrackerFinal {

    static ArrayList<Expense> list = new ArrayList<>();
    static DefaultListModel<String> model = new DefaultListModel<>();

    public static void main(String[] args) {

        JFrame f = new JFrame("Expense Tracker");

        // Labels
        JLabel l1 = new JLabel("Category:");
        l1.setBounds(30, 30, 100, 30);

        JLabel l2 = new JLabel("Amount:");
        l2.setBounds(30, 70, 100, 30);

        JLabel l3 = new JLabel("Date:");
        l3.setBounds(30, 110, 100, 30);

        // Total Label
        JLabel totalLabel = new JLabel("Total Expense: ₹0");
        totalLabel.setBounds(300, 290, 250, 30);

        // Input Fields
        JTextField categoryField = new JTextField();
        categoryField.setBounds(120, 30, 150, 30);

        JTextField amountField = new JTextField();
        amountField.setBounds(120, 70, 150, 30);

        // Date Picker
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setBounds(120, 110, 150, 30);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd-MM-yyyy");
        dateSpinner.setEditor(editor);

        // List Display
        JList<String> expenseList = new JList<>(model);
        JScrollPane scroll = new JScrollPane(expenseList);
        scroll.setBounds(300, 30, 250, 250);

        // Buttons
        JButton addBtn = new JButton("Add");
        addBtn.setBounds(30, 160, 80, 30);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(120, 160, 80, 30);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(210, 160, 80, 30);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(30, 210, 80, 30);

        JButton totalBtn = new JButton("Show Total");
        totalBtn.setBounds(120, 210, 120, 30);

        // Function to calculate total
        Runnable updateTotal = () -> {
            double total = 0;

            for (Expense ex : list) {
                total += ex.amount;
            }

            totalLabel.setText("Total Expense: ₹" + total);
        };

        // ADD FUNCTION
        addBtn.addActionListener(e -> {
            try {
                String category = categoryField.getText();

                if(category.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Category cannot be empty!");
                    return;
                }

                double amount = Double.parseDouble(amountField.getText());

                Date selectedDate = (Date) dateSpinner.getValue();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String date = sdf.format(selectedDate);

                Expense ex = new Expense(category, amount, date);
                list.add(ex);

                model.addElement(category + " | ₹" + amount + " | " + date);

                updateTotal.run();

                JOptionPane.showMessageDialog(f, "Expense Added!");

            } catch(Exception ex) {
                JOptionPane.showMessageDialog(f, "Enter valid data!");
            }
        });

        // LOAD SELECTED ITEM INTO FIELDS
        expenseList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = expenseList.getSelectedIndex();

                if(index != -1) {
                    Expense ex = list.get(index);

                    categoryField.setText(ex.category);
                    amountField.setText(String.valueOf(ex.amount));
                }
            }
        });

        // UPDATE FUNCTION
        updateBtn.addActionListener(e -> {

            int index = expenseList.getSelectedIndex();

            if(index != -1) {
                try {

                    String category = categoryField.getText();
                    double amount = Double.parseDouble(amountField.getText());

                    Date selectedDate = (Date) dateSpinner.getValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String date = sdf.format(selectedDate);

                    list.get(index).category = category;
                    list.get(index).amount = amount;
                    list.get(index).date = date;

                    model.set(index, category + " | ₹" + amount + " | " + date);

                    updateTotal.run();

                    JOptionPane.showMessageDialog(f, "Updated Successfully!");

                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(f, "Invalid Data!");
                }

            } else {
                JOptionPane.showMessageDialog(f, "Select an expense first!");
            }
        });

        // DELETE FUNCTION
        deleteBtn.addActionListener(e -> {

            int index = expenseList.getSelectedIndex();

            if(index != -1) {

                list.remove(index);
                model.remove(index);

                updateTotal.run();

                JOptionPane.showMessageDialog(f, "Deleted!");

            } else {
                JOptionPane.showMessageDialog(f, "Select an expense first!");
            }
        });

        // CLEAR FIELDS
        clearBtn.addActionListener(e -> {
            categoryField.setText("");
            amountField.setText("");
        });

        // SHOW TOTAL BUTTON
        totalBtn.addActionListener(e -> {
            updateTotal.run();
            JOptionPane.showMessageDialog(f, totalLabel.getText());
        });

        // Add components
        f.add(l1);
        f.add(l2);
        f.add(l3);

        f.add(categoryField);
        f.add(amountField);
        f.add(dateSpinner);

        f.add(addBtn);
        f.add(updateBtn);
        f.add(deleteBtn);
        f.add(clearBtn);
        f.add(totalBtn);

        f.add(scroll);
        f.add(totalLabel);

        // Frame settings
        f.setSize(600, 380);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}