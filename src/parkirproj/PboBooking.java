/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkirproj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.toedter.calendar.JDateChooser;
/**
 *
 * @author dhamar©2024
 */
public class PboBooking extends JFrame {
    private JTextField namaField, kendField, platField;
    private JDateChooser tglin, tglout; 
    private JButton psnBtn, backBtn;
    private JLabel kopTime;
    private JComboBox<String> bayarCbb;
    
    private String generateRandomCode(int length) {
        String characters = "0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int)(characters.length() * Math.random());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }
    
    public PboBooking() {
        setTitle("Parkir Purnama v1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(825, 540);
        setLocationRelativeTo(null);
        
        JLabel kopID = new JLabel("PARKIR PURNAMA");
        kopTime = new JLabel();
        JLabel bk1 = new JLabel("Nama pemesan: ");
        JLabel bk2 = new JLabel("Kendaraan: ");
        JLabel bk3 = new JLabel("Nomor TNKB (tanpa spasi): ");
        JLabel bk4 = new JLabel("Pilih tanggal check-in ");
        JLabel bk5 = new JLabel("Pilih tanggal check-out ");
        JLabel bk6 = new JLabel("Pilih metode bayar ");
        kopID.setFont(new Font("SansSerif", Font.BOLD, 22));
        bk1.setFont(new Font("SansSerif", Font.PLAIN, 18));
        bk2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        bk3.setFont(new Font("SansSerif", Font.PLAIN, 18));
        bk4.setFont(new Font("SansSerif", Font.PLAIN, 18));
        bk5.setFont(new Font("SansSerif", Font.PLAIN, 18));
        bk6.setFont(new Font("SansSerif", Font.PLAIN, 18));
        kopTime.setFont(new Font("Calibri", Font.PLAIN, 18));
        
        namaField = new JTextField(20);
        namaField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        kendField = new JTextField(20);
        kendField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        platField = new JTextField(20);
        platField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        
        tglin = new JDateChooser();
        tglin.setDateFormatString("dd/MM/yyyy");
        tglin.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tglin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tglout = new JDateChooser();
        tglout.setDateFormatString("dd/MM/yyyy");
        tglout.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tglout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        bayarCbb = new JComboBox<>(new String[] {"Pilih", "Transfer", "QRIS"});
        bayarCbb.setFont(new Font("SansSerif", Font.PLAIN, 12));
        bayarCbb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        psnBtn = new JButton("BAYAR");
        backBtn = new JButton("BACK");
        psnBtn.setFont(new Font("Calibri", Font.BOLD, 18));
        backBtn.setFont(new Font("Calibri", Font.BOLD, 18));
        psnBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        Date today = new Date();
        Date maxDate = new Date(today.getTime() + 7 * 24 * 60 * 60 * 1000);
        tglin.setMinSelectableDate(today);
        tglout.setMinSelectableDate(today);
        tglout.setMaxSelectableDate(maxDate);

        tglin.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())) {
                    Date selectedDate = tglin.getDate();
                    tglout.setMinSelectableDate(new Date(selectedDate.getTime() + 24 * 60 * 60 * 1000));
                    tglout.setMaxSelectableDate(new Date(selectedDate.getTime() + 7 * 24 * 60 * 60 * 1000));
                    
                    if (tglout.getDate() != null) {
                        Date currentOutDate = tglout.getDate();
                        if (currentOutDate.before(tglout.getMinSelectableDate())) {
                            tglout.setDate(null);
                        }
                    } else {
                        tglout.setDate(new Date(selectedDate.getTime() + 24 * 60 * 60 * 1000));
                    }
                }
            }
        });

        tglout.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())) {
                    Date selectedInDate = tglin.getDate();
                    Date selectedOutDate = tglout.getDate();
                    if (selectedOutDate != null && !selectedOutDate.after(selectedInDate)) {
                        tglout.setDate(null);
                    }
                }
            }
        });
        
        psnBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = namaField.getText().toUpperCase();
                String kend = kendField.getText().toUpperCase();
                String tnkb = platField.getText().toUpperCase();
                java.util.Date tglInDate = tglin.getDate();
                java.util.Date tglOutDate = tglout.getDate();
        
                if (nama.isEmpty() || kend.isEmpty() || tnkb.isEmpty() || tglin.getDate() == null || tglout.getDate() == null) {
                    JOptionPane.showMessageDialog(null,
                            "Mohon isi dan lengkapi semua data", "Notifikasi", JOptionPane.WARNING_MESSAGE);
                } else if (tnkb.length() > 9) {
                    JOptionPane.showMessageDialog(null,
                            "Panjang maksimal untuk TNKB adalah 9 karakter", "Notifikasi", JOptionPane.WARNING_MESSAGE);
                } else {
                    long diffInMillis = tglOutDate.getTime() - tglInDate.getTime();
                    long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);
                    String durasi = Long.toString(diffInDays);
                    try {
                        String kdBook = generateRandomCode(6);
                        Class.forName("oracle.jdbc.OracleDriver");
                        String url = "jdbc:oracle:thin:@localhost:1521:lepkomf4";
                        String username = "system";
                        String upass = "oracle";
                        Connection conn = DriverManager.getConnection(url, username, upass);

                        String sql = "INSERT INTO PESANAN (KD_BOOK, NAMA, KENDARAAN, PLATNO, TGLIN, TGLOUT, DURASI) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, kdBook);
                        pstmt.setString(2, nama);
                        pstmt.setString(3, kend);
                        pstmt.setString(4, tnkb);
                        java.sql.Date tgli = new java.sql.Date(tglInDate.getTime());
                        java.sql.Date tglo = new java.sql.Date(tglOutDate.getTime());
                        pstmt.setDate(5, tgli);
                        pstmt.setDate(6, tglo);
                        pstmt.setString(7, durasi);

                        int rowsInserted = pstmt.executeUpdate();

                        if (rowsInserted > 0) {
                            String byrFix = (String) bayarCbb.getSelectedItem();
                            if ("Pilih".equals(byrFix)) {
                                JOptionPane.showMessageDialog(null,
                                        "Pilih metode pembayaran", "Notifikasi", JOptionPane.WARNING_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Data berhasil disimpan", "Notifikasi", JOptionPane.INFORMATION_MESSAGE);
                                if ("Transfer".equals(byrFix)) {
                                    new PboTransfer(kdBook);
                                    dispose();
                                } else if ("QRIS".equals(byrFix)) {
                                    new PboQris(kdBook);
                                    dispose();
                                } 
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Gagal menyimpan data", "Notifikasi", JOptionPane.ERROR_MESSAGE);
                        }

                        pstmt.close();
                        conn.close();
                    }  catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,"ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });        
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PmeNu();
                dispose();
            }
        });
        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss");
                kopTime.setText(sdf.format(new Date()));
            }
        });
        timer.start();
        
        setLayout(new BorderLayout());
        JPanel kopPanel = new JPanel();
        kopPanel.setLayout(new BoxLayout(kopPanel, BoxLayout.Y_AXIS));
        kopID.setAlignmentX(Component.CENTER_ALIGNMENT);
        kopTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        kopPanel.add(Box.createVerticalStrut(25));
        kopPanel.add(kopID);
        kopPanel.add(Box.createVerticalStrut(7));
        kopPanel.add(kopTime);
        kopPanel.add(Box.createVerticalStrut(10));
        
        JPanel bkPanel = new JPanel();
        bkPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 10, 12);

        //nama
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        bkPanel.add(bk1, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        bkPanel.add(namaField, gbc);
        
        //kendaraan
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        bkPanel.add(bk2, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        bkPanel.add(kendField, gbc);
       
        //plat
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        bkPanel.add(bk3, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        bkPanel.add(platField, gbc);

        //tglIn
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        bkPanel.add(bk4, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        bkPanel.add(tglin, gbc);

        //tglOut
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        bkPanel.add(bk5, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        bkPanel.add(tglout, gbc);
        
        //bayar
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        bkPanel.add(bk6, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        bkPanel.add(bayarCbb, gbc);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        buttonPanel.add(backBtn);
        buttonPanel.add(psnBtn);
        
        add(kopPanel, BorderLayout.NORTH);
        add(bkPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}
