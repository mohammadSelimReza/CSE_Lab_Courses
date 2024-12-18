import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UserPanel extends javax.swing.JFrame {

    /**
     * Creates new form UserPanel
     */
    public UserPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        tfUserName = new javax.swing.JTextField();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        tfContact = new javax.swing.JTextField();
        javax.swing.JButton btnBook = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User Panel");
        setMinimumSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Available Vaccines");

        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Manufacture", "Doses"
            }
        ));
        jScrollPane1.setViewportView(tableUser);

        jLabel2.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel2.setText("Your Name");

        jLabel3.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabel3.setText("Contact Info");

        btnBook.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        btnBook.setText("Book Vaccine");
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(tfUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(tfContact, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfContact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBook)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {                                  
        // Load vaccine data from file
        try (FileInputStream file = new FileInputStream("file.bin");
             ObjectInputStream input = new ObjectInputStream(file)) {
            Vector<Vector> tableData = (Vector<Vector>) input.readObject();
            DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
            for (Vector row : tableData) {
                model.addRow(row.toArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                                          "Error loading vaccine data.",
                                          "Error",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }                                 

    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {                                        
        int selectedRow = tableUser.getSelectedRow();
        String userName = tfUserName.getText().trim();
        String contact = tfContact.getText().trim();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                                          "Please select a vaccine to book.",
                                          "Error",
                                          JOptionPane.ERROR_MESSAGE);
        } else if (userName.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                                          "Please enter your name and contact info.",
                                          "Error",
                                          JOptionPane.ERROR_MESSAGE);
        } else {
            String vaccineName = tableUser.getValueAt(selectedRow, 1).toString();
            JOptionPane.showMessageDialog(this,
                                          "Vaccine '" + vaccineName + "' booked successfully!\n"
                                          + "Name: " + userName + "\n"
                                          + "Contact: " + contact,
                                          "Booking Confirmed",
                                          JOptionPane.INFORMATION_MESSAGE);
            
            tfContact.setText("");
            tfUserName.setText("");
        }
    }                                       

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserPanel().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable tableUser;
    private javax.swing.JTextField tfContact;
    private javax.swing.JTextField tfUserName;
    // End of variables declaration
}
