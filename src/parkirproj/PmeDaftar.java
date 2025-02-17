/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkirproj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/**
 *
 * @author dhamar©2024
 */
public class PmeDaftar extends JFrame {
    private JTextField unameField;
    private JTextField passField;
    private JPasswordField rePassField;
    private JButton OKBtn;
    
    public PmeDaftar() {
        setTitle("Parkir Purnama v1.0");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 250);
        setLocationRelativeTo(null);
        
        JLabel unameLabel = new JLabel("Username baru Anda: ");
        unameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        unameField = new JTextField(15);
        unameField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        
        JLabel passLabel = new JLabel("Password baru Anda: ");
        passLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        passField = new JTextField(15);
        passField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        
        JLabel rePassLabel = new JLabel("Masukkan ulang Password: ");
        rePassLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        rePassField = new JPasswordField(15);
        rePassField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        
        OKBtn = new JButton("Buat Akun");
        OKBtn.setFont(new Font("Calibri", Font.BOLD, 20));
        OKBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        OKBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String uname = unameField.getText().trim();
                String pass = new String(passField.getText()).trim();
                String rePass = new String(rePassField.getPassword()).trim();
                
                if (uname.isEmpty()) {
                    JOptionPane.showMessageDialog(PmeDaftar.this,
                            "Username tidak boleh kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (pass.isEmpty()) {
                    JOptionPane.showMessageDialog(PmeDaftar.this,
                            "Password tidak boleh kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (rePass.isEmpty()) {
                    JOptionPane.showMessageDialog(PmeDaftar.this,
                            "Masukkan ulang password", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (!rePass.equals(pass)) {
                    JOptionPane.showMessageDialog(PmeDaftar.this,
                            "Password tidak sama", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (uname.length() <= 3 || pass.length() <= 3) {
                    JOptionPane.showMessageDialog(PmeDaftar.this,
                            "Username dan Password harus lebih dari 3 karakter", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        Class.forName("oracle.jdbc.OracleDriver");
                        String url = "jdbc:oracle:thin:@localhost:1521:lepkomf4";
                        String username = "system";
                        String upass = "oracle";
                        Connection conn = DriverManager.getConnection(url, username, upass);
                        
                        String sql = "INSERT INTO USERLOGIN (USERNAME, USER_PASSWORD) VALUES (?, ?)";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, uname);
                        pstmt.setString(2, pass);
                        
                        int rowsInserted = pstmt.executeUpdate();

                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(PmeDaftar.this,
                                    "Pembuatan Akun baru berhasil, silakan Login untuk melakukan pemesanan",
                                    "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
                            new PmeNu();
                            dispose();
                        }
                        
                        pstmt.close();
                        conn.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        setLayout(new FlowLayout());
        JPanel akunPanel = new JPanel();
        akunPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        //username
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        akunPanel.add(unameLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        akunPanel.add(unameField, gbc);
        
        //password
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        akunPanel.add(passLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        akunPanel.add(passField, gbc);
        
        //rePassword
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        akunPanel.add(rePassLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        akunPanel.add(rePassField, gbc);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
        buttonPanel.add(OKBtn);
        
        add(akunPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}
