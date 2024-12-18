import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {

    public HomePage() {
        // Setting up the JFrame
        setTitle("Vaccine Awareness");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Hero Section
        JPanel heroPanel = new JPanel();
        heroPanel.setBackground(new Color(51, 153, 255)); // Light blue background
        heroPanel.setLayout(new BorderLayout());

        // Hero Text (Title)
        JLabel heroTitle = new JLabel("Stay Safe, Get Vaccinated!", SwingConstants.CENTER);
        heroTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        heroTitle.setForeground(Color.WHITE);

        // Subtext
        JLabel subText = new JLabel("Protect yourself and your loved ones.", SwingConstants.CENTER);
        subText.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subText.setForeground(Color.WHITE);

        // Add title and subtext to hero panel
        heroPanel.add(heroTitle, BorderLayout.NORTH);
        heroPanel.add(subText, BorderLayout.CENTER);

        // Call-to-Action Button
        JButton ctaButton = new JButton("Book Your Vaccine Now");
        ctaButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        ctaButton.setBackground(new Color(255, 69, 0)); // Orange color
        ctaButton.setForeground(Color.WHITE);
        ctaButton.setFocusPainted(false);
        ctaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Button Action for Booking
        ctaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HomePage.this, "Redirecting to Booking System...");
                // Example: new UserPanel().setVisible(true); 
                // For now, just showing a message
            }
        });

        // Doctor Login Button
        JButton doctorLoginButton = new JButton("Doctor Login");
        doctorLoginButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        doctorLoginButton.setBackground(new Color(0, 204, 102)); // Green color
        doctorLoginButton.setForeground(Color.WHITE);
        doctorLoginButton.setFocusPainted(false);
        doctorLoginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Button Action for Doctor Login
        doctorLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the login page (Doctor Login)
                new LoginPage().setVisible(true);  // Assuming you have a LoginPage class
                HomePage.this.dispose(); // Close the homepage after opening login page
            }
        });

        // Adding hero panel, buttons to the main panel
        mainPanel.add(heroPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(ctaButton);
        buttonPanel.add(doctorLoginButton); // Add the Doctor Login button here
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adding main panel to frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }
}
