import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class EXPENSETRACKERGUI extends JFrame {

    // GUI Components
    JComboBox<String> dayBox;
    JComboBox<String> categoryBox;

    JTextField amountField;
    JTextField descriptionField;

    JTextArea outputArea;

    // Store all expenses
    ArrayList<Expense> expenses;

    // Constructor
    public EXPENSETRACKERGUI() {

        expenses = new ArrayList<>();

        // Window settings
        setTitle("Weekly Expense Tracker");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ==========================
        // INPUT PANEL
        // ==========================

        JPanel inputPanel = new JPanel();

        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));

        // Day dropdown
        JLabel dayLabel = new JLabel("Select Day");

        inputPanel.add(dayLabel);

        String[] days = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
        };

        dayBox = new JComboBox<>(days);

        inputPanel.add(dayBox);

        // Category dropdown
        JLabel categoryLabel =
                new JLabel("Select Category");

        inputPanel.add(categoryLabel);

        String[] categories = {
                "Groceries",
                "Eating Out",
                "Petrol",
                "Taxi",
                "Bills",
                "Rent",
                "Others"
        };

        categoryBox =
                new JComboBox<>(categories);

        inputPanel.add(categoryBox);

        // Amount field
        JLabel amountLabel =
                new JLabel("Enter Amount");

        inputPanel.add(amountLabel);

        amountField = new JTextField();

        inputPanel.add(amountField);

        // Description field
        JLabel descriptionLabel =
                new JLabel("Description");

        inputPanel.add(descriptionLabel);

        descriptionField = new JTextField();

        inputPanel.add(descriptionField);

        // Buttons
        JButton addButton =
                new JButton("Add Expense");

        JButton totalButton =
                new JButton("Show Weekly Total");

        inputPanel.add(addButton);
        inputPanel.add(totalButton);

        add(inputPanel, BorderLayout.NORTH);



        outputArea = new JTextArea();

        outputArea.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(outputArea);

        add(scrollPane, BorderLayout.CENTER);

        // Bottom button
        JButton categoryButton =
                new JButton("View Expenses By Category");

        add(categoryButton, BorderLayout.SOUTH);



        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String day =
                            (String) dayBox.getSelectedItem();

                    String category =
                            (String) categoryBox.getSelectedItem();

                    String amountText =
                            amountField.getText();

                    String description =
                            descriptionField.getText();

                    if (amountText.isEmpty()
                            || description.isEmpty()) {

                        JOptionPane.showMessageDialog(null,
                                "Please fill all fields");

                        return;
                    }

                    double amount =
                            Double.parseDouble(amountText);

                    // Negative validation
                    if (amount <= 0) {

                        JOptionPane.showMessageDialog(null,
                                "Amount must be positive");

                        return;
                    }

                    Expense expense =
                            new Expense(
                                    day,
                                    category,
                                    amount,
                                    description
                            );

                    // Add to ArrayList
                    expenses.add(expense);

                    // Show added expense
                    outputArea.append(
                            "Expense Added Successfully\n"
                    );

                    outputArea.append(
                            expense.toString() + "\n\n"
                    );

                    // Clear fields
                    amountField.setText("");

                    descriptionField.setText("");

                }

                catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(null,
                            "Please enter valid amount");

                }
            }
        });



        totalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                double total = 0;

                for (Expense expense : expenses) {

                    total =
                            total + expense.getAmount();
                }

                outputArea.append(
                        "Total Weekly Expense = $"
                                + total + "\n\n"
                );
            }
        });


        categoryButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                HashMap<String, Double> categoryTotals =
                        new HashMap<>();

                // Group expenses
                for (Expense expense : expenses) {

                    String category =
                            expense.getCategory();

                    if (categoryTotals.containsKey(category)) {

                        double currentAmount =
                                categoryTotals.get(category);

                        categoryTotals.put(
                                category,
                                currentAmount
                                        + expense.getAmount()
                        );
                    }

                    else {

                        categoryTotals.put(
                                category,
                                expense.getAmount()
                        );
                    }
                }

                outputArea.append(
                        "Expenses Grouped By Category\n"
                );

                for (String category :
                        categoryTotals.keySet()) {

                    outputArea.append(
                            category
                                    + " : $"
                                    + categoryTotals.get(category)
                                    + "\n"
                    );
                }

                outputArea.append("\n");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        new EXPENSETRACKERGUI();
    }
}