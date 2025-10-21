import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentRegistrationForm extends JFrame implements ActionListener {
    // Components
    JLabel title, nameLabel, ageLabel, genderLabel, emailLabel, courseLabel;
    JTextField nameField, ageField, emailField;
    JRadioButton male, female;
    ButtonGroup genderGroup;
    JComboBox<String> courseBox;
    JButton submitBtn, resetBtn;
    JTextArea outputArea;

    // Constructor
    StudentRegistrationForm() {
        // Frame setup
        setTitle("Student Registration Form");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Title
        title = new JLabel("Student Registration Form", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(70, 20, 350, 30);
        add(title);

        // Name
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 100, 25);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(150, 80, 250, 25);
        add(nameField);

        // Age
        ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 120, 100, 25);
        add(ageLabel);
        ageField = new JTextField();
        ageField.setBounds(150, 120, 250, 25);
        add(ageField);

        // Gender
        genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 160, 100, 25);
        add(genderLabel);
        male = new JRadioButton("Male");
        male.setBounds(150, 160, 80, 25);
        female = new JRadioButton("Female");
        female.setBounds(230, 160, 100, 25);
        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        add(male);
        add(female);

        // Email
        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 200, 100, 25);
        add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(150, 200, 250, 25);
        add(emailField);

        // Course
        courseLabel = new JLabel("Course:");
        courseLabel.setBounds(50, 240, 100, 25);
        add(courseLabel);
        String[] courses = {"B.Tech", "BCA", "B.Sc", "MBA", "M.Tech"};
        courseBox = new JComboBox<>(courses);
        courseBox.setBounds(150, 240, 250, 25);
        add(courseBox);

        // Buttons
        submitBtn = new JButton("Submit");
        submitBtn.setBounds(100, 300, 120, 30);
        resetBtn = new JButton("Reset");
        resetBtn.setBounds(250, 300, 120, 30);
        add(submitBtn);
        add(resetBtn);

        submitBtn.addActionListener(this);
        resetBtn.addActionListener(this);

        // Output area
        outputArea = new JTextArea();
        outputArea.setBounds(50, 360, 370, 150);
        outputArea.setEditable(false);
        add(outputArea);
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            String name = nameField.getText();
            String age = ageField.getText();
            String gender = male.isSelected() ? "Male" : female.isSelected() ? "Female" : "Not Selected";
            String email = emailField.getText();
            String course = (String) courseBox.getSelectedItem();

            // Simple validation
            if (name.isEmpty() || age.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!");
                return;
            }

            // Display info
            outputArea.setText("✅ Registration Successful!\n\n" +
                    "Name: " + name + "\n" +
                    "Age: " + age + "\n" +
                    "Gender: " + gender + "\n" +
                    "Email: " + email + "\n" +
                    "Course: " + course);
        } 
        else if (e.getSource() == resetBtn) {
            nameField.setText("");
            ageField.setText("");
            emailField.setText("");
            genderGroup.clearSelection();
            courseBox.setSelectedIndex(0);
            outputArea.setText("");
        }
    }

    // Main method
    public static void main(String[] args) {
        new StudentRegistrationForm().setVisible(true);
    }
}
