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
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author dhamar©2024
 */
public class PAwelcom extends JFrame {
    private JButton exitBtn, bookBtn;
    private JLabel kopTime;
    
    public PAwelcom() {
        setTitle("Parkir Purnama v1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 535);
        setLocationRelativeTo(null);
        
        JLabel kopID = new JLabel("PARKIR PURNAMA");
        JLabel kopAdrs = new JLabel("Jl. Raya Bandara No. 110, Jakarta, Indonesia");
        kopTime = new JLabel();
        JLabel ket1 = new JLabel ("Bingung mencari tempat untuk menitipkan kendaraan pribadi");
        JLabel kett1 = new JLabel ("maupun kendaraan dinas Anda saat bepergian keluar kota?");
        JLabel ket2 = new JLabel ("Tenang! Kami solusi yang tepat untuk Anda!");
        JLabel ket3 = new JLabel ("Kami menyediakan tempat untuk menginap kendaraan Anda");
        JLabel ket4 = new JLabel ("BOOK sekarang dan dapatkan promo menarik*");
        kopID.setFont(new Font("SansSerif", Font.BOLD, 22));
        kopAdrs.setFont(new Font("SansSerif", Font.BOLD, 15));
        kopTime.setFont(new Font("Calibri", Font.PLAIN, 17));
        ket1.setFont(new Font("SansSerif", Font.PLAIN, 20));
        kett1.setFont(new Font("SansSerif", Font.PLAIN, 20));
        ket2.setFont(new Font("SansSerif", Font.PLAIN, 20));
        ket3.setFont(new Font("SansSerif", Font.PLAIN, 20));
        ket4.setFont(new Font("SansSerif", Font.PLAIN, 17));
        
        JPanel kopPanel = new JPanel();
        kopPanel.setLayout(new BoxLayout(kopPanel, BoxLayout.Y_AXIS));
        kopID.setAlignmentX(Component.CENTER_ALIGNMENT);
        kopAdrs.setAlignmentX(Component.CENTER_ALIGNMENT);
        kopTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        kopPanel.add(Box.createVerticalStrut(25));
        kopPanel.add(kopID);
        kopPanel.add(Box.createVerticalStrut(2));
        kopPanel.add(kopAdrs);
        kopPanel.add(Box.createVerticalStrut(7));
        kopPanel.add(kopTime);
        kopPanel.add(Box.createVerticalStrut(42));
        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss");
                kopTime.setText(sdf.format(new Date()));
            }
        });
        timer.start();
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        ket1.setAlignmentX(Component.CENTER_ALIGNMENT);
        kett1.setAlignmentX(Component.CENTER_ALIGNMENT);
        ket2.setAlignmentX(Component.CENTER_ALIGNMENT);
        ket3.setAlignmentX(Component.CENTER_ALIGNMENT);
        ket4.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(ket1);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(kett1);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(ket2);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(ket3);
        infoPanel.add(Box.createVerticalStrut(28));
        infoPanel.add(ket4);
        
        exitBtn = new JButton("EXIT");
        bookBtn = new JButton("BOOK");
        exitBtn.setFont(new Font("Calibri", Font.BOLD, 20));
        bookBtn.setFont(new Font("Calibri", Font.BOLD, 20));
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bookBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn.setBackground(Color.RED);
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setBorderPainted(false);
        
        exitBtn.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e){
                int response = JOptionPane.showConfirmDialog(PAwelcom.this,
                        "Ingin keluar aplikasi?", "Parkir Purnama v1.0", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        bookBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PmeNu();
                dispose();
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 75));
        buttonPanel.add(exitBtn);
        buttonPanel.add(bookBtn);
        
        setLayout(new BorderLayout());
        add(kopPanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}
