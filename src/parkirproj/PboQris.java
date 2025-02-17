/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkirproj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author dhamar©2024
 */
public class PboQris extends JFrame {
    private JButton exitBtn;
    private String kdBook;
    
    public PboQris(String kdBook) {
        this.kdBook = kdBook;
        setTitle("Parkir Purnama v1.0");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(420, 500);
        setLocationRelativeTo(null);
        
        JLabel kopID1 = new JLabel("Scan QR Code dibawah");
        JLabel kopID2 = new JLabel("untuk menyelesaikan pembayaran");
        kopID1.setFont(new Font("SansSerif", Font.BOLD, 18));
        kopID2.setFont(new Font("SansSerif", Font.BOLD, 18));
        
        exitBtn = new JButton("Dapatkan Kode BOOK");
        exitBtn.setFont(new Font("Calibri", Font.BOLD, 18));
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        exitBtn.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e){
                int response = JOptionPane.showConfirmDialog(null,
                    "Apakah Anda sudah membayar?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
                if (response == JOptionPane.YES_OPTION) {
                   JOptionPane.showMessageDialog(null,
                           "Terima kasih sudah memesan!", "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
                    new PboKdbook(kdBook);
                    dispose();
                } 
            }
        });
        
        JPanel kopPanel = new JPanel();
        kopPanel.setLayout(new BoxLayout(kopPanel, BoxLayout.Y_AXIS));
        kopID1.setAlignmentX(Component.CENTER_ALIGNMENT);
        kopID2.setAlignmentX(Component.CENTER_ALIGNMENT);
        kopPanel.add(Box.createVerticalStrut(45));
        kopPanel.add(kopID1);
        kopPanel.add(Box.createVerticalStrut(3));
        kopPanel.add(kopID2);
        kopPanel.add(Box.createVerticalStrut(20));
        
        JPanel qrPanel = new JPanel();
        qrPanel.setLayout(new BorderLayout());
        ImageIcon qrIcon = new ImageIcon("file-path-image");
        Image oriImage = qrIcon.getImage();
        Image sesImage = oriImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon sesIcon = new ImageIcon(sesImage);
        JLabel qrLabel = new JLabel(sesIcon);
        qrPanel.add(qrLabel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
        buttonPanel.add(exitBtn);
        
        setLayout(new BorderLayout());
        add(kopPanel, BorderLayout.NORTH);
        add(qrPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }    
}
