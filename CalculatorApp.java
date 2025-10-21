import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorApp extends JFrame implements ActionListener {

    // Components
    JTextField display;
    JButton[] numberButtons = new JButton[10];
    JButton addBtn, subBtn, mulBtn, divBtn, eqBtn, clrBtn;
    String operator = "";
    double num1 = 0, num2 = 0;

    CalculatorApp() {
        // Frame setup
        setTitle("Calculator App");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Display
        display = new JTextField();
        display.setBounds(30, 30, 230, 40);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 18));
        add(display);

        // Number buttons
        int x = 30, y = 80;
        for (int i = 1; i <= 9; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setBounds(x, y, 50, 50);
            numberButtons[i].addActionListener(this);
            add(numberButtons[i]);
            x += 60;
            if (i % 3 == 0) {
                x = 30;
                y += 60;
            }
        }
        // Button 0
        numberButtons[0] = new JButton("0");
        numberButtons[0].setBounds(90, y, 50, 50);
        numberButtons[0].addActionListener(this);
        add(numberButtons[0]);

        // Operator buttons
        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        eqBtn = new JButton("=");
        clrBtn = new JButton("C");

        JButton[] ops = {addBtn, subBtn, mulBtn, divBtn, eqBtn, clrBtn};
        x = 210; y = 80;
        for (int i = 0; i < ops.length; i++) {
            ops[i].setBounds(x, y, 50, 50);
            ops[i].addActionListener(this);
            add(ops[i]);
            y += 60;
        }
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i <= 9; i++) {
            if (e.getSource() == numberButtons[i]) {
                display.setText(display.getText() + i);
                return;
            }
        }

        if (e.getSource() == addBtn) {
            num1 = Double.parseDouble(display.getText());
            operator = "+";
            display.setText("");
        } else if (e.getSource() == subBtn) {
            num1 = Double.parseDouble(display.getText());
            operator = "-";
            display.setText("");
        } else if (e.getSource() == mulBtn) {
            num1 = Double.parseDouble(display.getText());
            operator = "*";
            display.setText("");
        } else if (e.getSource() == divBtn) {
            num1 = Double.parseDouble(display.getText());
            operator = "/";
            display.setText("");
        } else if (e.getSource() == eqBtn) {
            try {
                num2 = Double.parseDouble(display.getText());
                double result = 0;
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0) {
                            JOptionPane.showMessageDialog(this, "❌ Cannot divide by zero!");
                            display.setText("");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                display.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Invalid Input!");
                display.setText("");
            }
        } else if (e.getSource() == clrBtn) {
            display.setText("");
            num1 = num2 = 0;
            operator = "";
        }
    }

    // Main method
    public static void main(String[] args) {
        new CalculatorApp().setVisible(true);
    }
}
