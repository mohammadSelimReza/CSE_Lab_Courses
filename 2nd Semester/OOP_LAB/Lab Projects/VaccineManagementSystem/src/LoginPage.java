import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class LoginPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        // JFrame Setup
        setTitle("Doctor Login");
        setSize(400, 350);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Prevent resizing the window

        // Creating the main panel with a background color
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240)); // Light grey background
        mainPanel.setLayout(new BorderLayout());

        // Creating header panel for title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 123, 255)); // Blue color for header
        JLabel headerLabel = new JLabel("Doctor Login");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        
        // Panel for login form
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255)); // White background for form
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));

        // Username and Password fields
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Login and Sign Up Buttons
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 123, 255)); // Blue button
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Arial", Font.BOLD, 14));
        signupButton.setBackground(new Color(0, 123, 255)); // Blue button
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add components to form panel
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(loginButton);
        formPanel.add(signupButton);

        // Adding panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        add(mainPanel);

        // Action for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPage.this,
                            "Please enter both username and password.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (checkLogin(username, password)) {
                        JOptionPane.showMessageDialog(LoginPage.this,
                                "Login Successful!",
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        // Redirect to Admin Panel
                        redirectToAdminPanel();
                    } else {
                        JOptionPane.showMessageDialog(LoginPage.this,
                                "Invalid Username or Password.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Action for sign up button
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open sign-up form
                showSignUpForm();
            }
        });
    }

    private boolean checkLogin(String username, String password) {
        ArrayList<Doctor> doctors = loadDoctorData();
        for (Doctor doctor : doctors) {
            if (doctor.getUsername().equals(username) && doctor.getPassword().equals(password)) {
                return true; // Login success
            }
        }
        return false; // Login failure
    }

    private ArrayList<Doctor> loadDoctorData() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream("doctorData.bin");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            doctors = (ArrayList<Doctor>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading doctor data. Please try again later.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return doctors;
    }

    private void showSignUpForm() {
        // Create the sign-up form
        JFrame signUpFrame = new JFrame("Sign Up");
        signUpFrame.setSize(400, 300);
        signUpFrame.setLocationRelativeTo(this); // Center on parent frame
        signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signUpFrame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton submitButton = new JButton("Submit");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(submitButton);

        signUpFrame.add(panel);
        signUpFrame.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(signUpFrame,
                            "Please enter both username and password.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Doctor newDoctor = new Doctor(username, password);
                    saveNewDoctor(newDoctor);
                    JOptionPane.showMessageDialog(signUpFrame,
                            "Doctor account created successfully!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    signUpFrame.dispose();
                }
            }
        });
    }

    private void saveNewDoctor(Doctor doctor) {
        ArrayList<Doctor> doctors = loadDoctorData();
        doctors.add(doctor);
        try (FileOutputStream fileOutputStream = new FileOutputStream("doctorData.bin");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(doctors);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error saving new doctor data. Please try again later.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Redirect to Admin Panel
    private void redirectToAdminPanel() {
        // Create and display the Admin Panel window
        AdminPanel adminPanel = new AdminPanel();
        adminPanel.setVisible(true);
        // Close the login page
        this.dispose();
    }

    // Doctor class for storing username and password
    public static class Doctor implements Serializable {
        private String username;
        private String password;

        public Doctor(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }
}
