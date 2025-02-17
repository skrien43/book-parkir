/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkirproj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author dhamar
 */
public class PboKdbook extends JFrame {
    private JLabel kode, ket;
    
    public PboKdbook(String kdBook) {
        setTitle("Parkir Purnama v1.0");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(455, 200);
        setLocationRelativeTo(null);
        
        kode = new JLabel("Kode BOOK Anda adalah: " + kdBook);
        kode.setFont(new Font("SansSerif", Font.BOLD, 18));
        kode.setHorizontalAlignment(SwingConstants.CENTER);
        ket = new JLabel("Tunjukkan bukti pembayaran pada saat check-in.");
        ket.setFont(new Font("SansSerif", Font.BOLD, 16));
        ket.setHorizontalAlignment(SwingConstants.CENTER);
        
        setLayout(new BorderLayout());
        JPanel kodePanel = new JPanel();
        kodePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 1;
        kodePanel.add(kode, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        kodePanel.add(ket, gbc);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(PboKdbook.this,
                        "Kembali ke menu utama", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
                if (response == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        
        add(kodePanel, BorderLayout.NORTH);
        setVisible(true);
    }
}
