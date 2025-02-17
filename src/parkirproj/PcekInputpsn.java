/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkirproj;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
/**
 *
 * @author dhamar©2024
 */
public class PcekInputpsn extends JFrame {
    private JTextField kdBField;
    private JButton OKBtn;
    private String kdBook;
    private JLabel infoPsn;
    
    public PcekInputpsn() {
        setTitle("Parkir Purnama v1.0");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 220);
        setLocationRelativeTo(null);
        
        JLabel kdBLabel = new JLabel("Masukkan kode BOOK Anda: ");
        kdBLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        kdBField = new JTextField(10);
        kdBField.setFont(new Font("SansSerif", Font.BOLD, 13));
        
        OKBtn = new JButton("Cek Pesanan");
        OKBtn.setFont(new Font("Calibri", Font.BOLD, 20));
        OKBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        infoPsn = new JLabel("");
        infoPsn.setFont(new Font("SansSerif", Font.BOLD, 14));
        
        OKBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String kdBk = kdBField.getText().trim();
                
                if (kdBk.isEmpty()) {
                    JOptionPane.showMessageDialog(PcekInputpsn.this,
                            "Masukkan kode book Anda", "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (cekValid(kdBk)) {
                        viewPsn(kdBk);
                        new PcekHasilpsn(infoPsn.getText());
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(PcekInputpsn.this,
                                "Kode book tidak valid", "Peringatan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        JPanel kdPanel = new JPanel();
        kdPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
        kdPanel.add(kdBLabel);
        kdPanel.add(kdBField);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 45));
        buttonPanel.add(OKBtn);
        
        setLayout(new BorderLayout());
        add(kdPanel, BorderLayout.CENTER);                        
        add(buttonPanel, BorderLayout.SOUTH);                        
        setVisible(true);
    }
    
    private boolean cekValid(String kdBk) {
        boolean isValid = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:lepkomf4";
            String username = "system";
            String upass = "oracle";
            conn = DriverManager.getConnection(url, username, upass);

            String sql = "SELECT COUNT(*) FROM PESANAN WHERE KD_BOOK = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kdBk);
            rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                isValid = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return isValid;
    }
    
    private void viewPsn(String kdBk) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:lepkomf4";
            String username = "system";
            String upass = "oracle";
            conn = DriverManager.getConnection(url, username, upass);

            String sql = "SELECT NAMA, DURASI, TGLIN FROM PESANAN WHERE KD_BOOK = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kdBk);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String nama = rs.getString("NAMA");
                String durasi = rs.getString("DURASI");
                Date tglin = rs.getDate("TGLIN");
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
                String tglinFmt = sdf.format(tglin);

                String info = String.format("<html>Booking atas nama %s selama %s malam.<br>Check-in pada tanggal %s</html>.", nama, durasi, tglinFmt);
                infoPsn.setText(info);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
