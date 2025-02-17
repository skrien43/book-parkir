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
/**
 *
 * @author dhamar
 */
public class PcekHasilpsn extends JFrame {
    private JLabel hasil;
    private JButton menu, ext;
    
    public PcekHasilpsn(String infoPsnText) {
        setTitle("Parkir Purnama v1.0");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 200);
        setLocationRelativeTo(null);

        hasil = new JLabel(infoPsnText);
        hasil.setFont(new Font("SansSerif", Font.BOLD, 16));
        hasil.setHorizontalAlignment(SwingConstants.CENTER);
        
        menu = new JButton("MENU");
        menu.setFont(new Font("Calibri", Font.BOLD, 20));
        menu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        ext = new JButton("EXIT");
        ext.setFont(new Font("Calibri", Font.BOLD, 20));
        ext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ext.setBackground(Color.RED);
        ext.setForeground(Color.WHITE);
        ext.setBorderPainted(false);

        setLayout(new BorderLayout());
        add(hasil, BorderLayout.CENTER);
        
        ext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(PcekHasilpsn.this,
                        "Apakah Anda ingin keluar?", "Parkir Purnama v1.0", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(PcekHasilpsn.this,
                        "Apakah Anda ingin kembali ke menu?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
        buttonPanel.add(menu);
        buttonPanel.add(ext);
        
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}
