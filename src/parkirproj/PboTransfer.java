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
public class PboTransfer extends JFrame {
    private JButton exitBtn;
    private String kdBook;
    
    public PboTransfer(String kdBook) {
        this.kdBook = kdBook;
        setTitle("Parkir Purnama v1.0");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 250);
        setLocationRelativeTo(null);
        
        JLabel txt1 = new JLabel("Transfer ke nomor Virtual Account dibawah ini");
        JLabel txt2 = new JLabel("BANK KOOX  8001 1234 5678 9012");
        JLabel txt3 = new JLabel("a/n Purnama Parking");
        txt1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        txt2.setFont(new Font("SansSerif", Font.BOLD, 16));
        txt3.setFont(new Font("SansSerif", Font.BOLD, 16));
        
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
        txt1.setAlignmentX(Component.CENTER_ALIGNMENT);
        txt2.setAlignmentX(Component.CENTER_ALIGNMENT);
        txt3.setAlignmentX(Component.CENTER_ALIGNMENT);
        kopPanel.add(Box.createVerticalStrut(35));
        kopPanel.add(txt1);
        kopPanel.add(Box.createVerticalStrut(7));
        kopPanel.add(txt2);
        kopPanel.add(Box.createVerticalStrut(7));
        kopPanel.add(txt3);
        kopPanel.add(Box.createVerticalStrut(17));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 48));
        buttonPanel.add(exitBtn);
        
        setLayout(new BorderLayout());
        add(kopPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
    
}
