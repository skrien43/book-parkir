/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkirproj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author dhamar©2024
 */
public class PmeNu extends JFrame {
    private JButton backBtn, loginBtn, cekBtn, daftarBtn;
    private JLabel kopTime;
    
    public PmeNu() {
        setTitle("Parkir Purnama v1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 535);
        setLocationRelativeTo(null);
        
        JLabel kopID = new JLabel("PARKIR PURNAMA");
        kopID.setFont(new Font("SansSerif", Font.BOLD, 22));
        JLabel menu1 = new JLabel("Pesan Sekarang");
        menu1.setFont(new Font("SansSerif", Font.BOLD, 20));
        JLabel menu2 = new JLabel("Belum punya akun?   Buat sekarang"); 
        menu2.setFont(new Font("SansSerif", Font.BOLD, 20));
        kopTime = new JLabel();
        kopTime.setFont(new Font("Calibri", Font.PLAIN, 20));
        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss");
                kopTime.setText(sdf.format(new Date()));
            }
        });
        timer.start();
        
        loginBtn = new JButton("LOGIN");
        backBtn = new JButton("BACK");
        cekBtn = new JButton("Cek Pesanan");
        daftarBtn = new JButton("Buat Akun");
        loginBtn.setFont(new Font("Calibri", Font.BOLD, 20));
        backBtn.setFont(new Font("Calibri", Font.BOLD, 20));
        daftarBtn.setFont(new Font("Calibri", Font.BOLD, 20));
        cekBtn.setFont(new Font("Calibri", Font.BOLD, 20));
        loginBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cekBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        daftarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PAwelcom();
                dispose();
            }
        });
        
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PmeLogin();
            }
        });
        
        cekBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PcekInputpsn();
            }
        });
        
        daftarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PmeDaftar();
            }
        });
        
        setLayout(new BorderLayout());
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;

        //menu2,daftarBtn
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCenter.add(menu2, gbc);
        gbc.gridx = 3;
        panelCenter.add(daftarBtn, gbc);

        //menu1,loginBtn
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCenter.add(menu1, gbc);
        gbc.gridx = 3;
        panelCenter.add(loginBtn, gbc);
        
        JPanel kopPanel = new JPanel();
        kopPanel.setLayout(new BoxLayout(kopPanel, BoxLayout.Y_AXIS));
        kopID.setAlignmentX(Component.CENTER_ALIGNMENT);
        kopTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        kopPanel.add(Box.createVerticalStrut(25));
        kopPanel.add(kopID);
        kopPanel.add(Box.createVerticalStrut(7));
        kopPanel.add(kopTime);
        kopPanel.add(Box.createVerticalStrut(50));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 85));
        buttonPanel.add(backBtn);
        buttonPanel.add(cekBtn);
        
        add(kopPanel, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}
