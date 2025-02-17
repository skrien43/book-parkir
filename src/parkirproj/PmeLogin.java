/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkirproj;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author dhamar©2024
 */
public class PmeLogin extends JFrame {
    private JTextField unameField;
    private JPasswordField passField;
    private JButton loginBtn;
    
    public PmeLogin() {
        setTitle("Parkir Purnama v1.0");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 220);
        setLocationRelativeTo(null);
        
        JLabel unameLabel = new JLabel("Masukkan Username: ");
        unameField = new JTextField(15);
        unameLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
        unameField.setFont(new Font("SansSerif", Font.PLAIN, 12));
        JLabel passLabel = new JLabel("Masukkan Password: ");
        passField = new JPasswordField(15);
        passLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
        passField.setFont(new Font("SansSerif", Font.PLAIN, 12));
        
        loginBtn = new JButton("LOGIN");
        loginBtn.setFont(new Font("Calibri", Font.BOLD, 20));
        loginBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBtn.setBackground(Color.GREEN);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBorderPainted(false);
        
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String uname = unameField.getText().trim();
                String pass = new String(passField.getPassword()).trim();
                
                if (uname.isEmpty()) {
                    JOptionPane.showMessageDialog(PmeLogin.this,
                            "Username tidak boleh kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (pass.isEmpty()) {
                    JOptionPane.showMessageDialog(PmeLogin.this,
                            "Masukkan Password", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        Class.forName("oracle.jdbc.OracleDriver");
                        String url = "jdbc:oracle:thin:@localhost:1521:lepkomf4";
                        String username = "system";
                        String upass = "oracle";
                        Connection conn = DriverManager.getConnection(url, username, upass);

                        String sql = "SELECT * FROM USERLOGIN WHERE USERNAME = ? AND USER_PASSWORD = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, uname);
                        pstmt.setString(2, pass);
                        ResultSet rs = pstmt.executeQuery();
                        
                        if(rs.next()) {
                            JOptionPane.showMessageDialog(PmeLogin.this,
                                    "Login Berhasil", "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
                            new PboBooking();
                            dispose();
                        } else {
                            sql = "SELECT * FROM USERLOGIN WHERE USERNAME = ?";
                            pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, uname);
                            rs = pstmt.executeQuery();
                            if (rs.next()) {
                                JOptionPane.showMessageDialog(PmeLogin.this,
                                        "Login gagal, Password salah", "ERROR", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(PmeLogin.this,
                                        "Login gagal, Username salah", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        
                        rs.close();
                        pstmt.close();
                        conn.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(PmeLogin.this, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
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
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
        buttonPanel.add(loginBtn);
        
        add(akunPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}
