import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementSystem extends JFrame {
    private Connection conn;
    private JTable table;
    private JTextField idField, nameField;
    private JButton addBtn, issueBtn, returnBtn, refreshBtn;
    private DefaultTableModel model;

    public LibraryManagementSystem() {
        // Initialize database connection
        connectDB();
        
        // Set up the UI
        setTitle("Library Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        // Create main panel
        JPanel panel = new JPanel(new BorderLayout());
        
        // Create input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Book ID:"));
        idField = new JTextField(10);
        inputPanel.add(idField);
        
        inputPanel.add(new JLabel("Book Name:"));
        nameField = new JTextField(15);
        inputPanel.add(nameField);
        
        addBtn = new JButton("Add Book");
        inputPanel.add(addBtn);
        
        panel.add(inputPanel, BorderLayout.NORTH);
        
        // Create table
        model = new DefaultTableModel(new String[]{"ID", "Name", "Status"}, 0);
        table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        issueBtn = new JButton("Issue Book");
        returnBtn = new JButton("Return Book");
        refreshBtn = new JButton("Refresh");
        
        buttonPanel.add(issueBtn);
        buttonPanel.add(returnBtn);
        buttonPanel.add(refreshBtn);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Button actions
        addBtn.addActionListener(e -> addBook());
        issueBtn.addActionListener(e -> issueBook());
        returnBtn.addActionListener(e -> returnBook());
        refreshBtn.addActionListener(e -> loadBooks());
        
        add(panel);
        loadBooks();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void connectDB() {
        try {
            // Replace with your database connection details
            String url = "jdbc:mysql://localhost:3306/library";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Connection Error: " + e.getMessage());
        }
    }

    private void loadBooks() {
        try {
            model.setRowCount(0); // Clear table
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books ORDER BY id");
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("status")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading books: " + e.getMessage());
        }
    }

    private void addBook() {
        String idText = idField.getText().trim();
        String name = nameField.getText().trim();
        if (idText.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both Book ID and Name!");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO books(id, name, status) VALUES(?, ?, 'available')");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book Added Successfully!");
            loadBooks();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Book ID must be a number!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    private void issueBook() {
        String idText = idField.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Book ID to issue!");
            return;
        }
        try {
            int id = Integer.parseInt(idText);
            PreparedStatement ps = conn.prepareStatement("UPDATE books SET status='issued' WHERE id=? AND status='available'");
            ps.setInt(1, id);
            int updated = ps.executeUpdate();
            if (updated > 0) 
                JOptionPane.showMessageDialog(this, "Book Issued Successfully!");
            else 
                JOptionPane.showMessageDialog(this, "Book Not Available or Already Issued!");
            loadBooks();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Book ID must be a number!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    private void returnBook() {
        String idText = idField.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Book ID to return!");
            return;
        }
        try {
            int id = Integer.parseInt(idText);
            PreparedStatement ps = conn.prepareStatement("UPDATE books SET status='available' WHERE id=? AND status='issued'");
            ps.setInt(1, id);
            int updated = ps.executeUpdate();
            if (updated > 0) 
                JOptionPane.showMessageDialog(this, "Book Returned Successfully!");
            else 
                JOptionPane.showMessageDialog(this, "Invalid Book ID or Book Not Issued!");
            loadBooks();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Book ID must be a number!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryManagementSystem::new);
    }
}