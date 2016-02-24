import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CancelReservation {

    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void crWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CancelReservation window = new CancelReservation();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public CancelReservation() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 450, 300);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblCancelReservation = new JLabel("Cancel Reservation");
        lblCancelReservation.setForeground(Color.ORANGE);
        lblCancelReservation.setBounds(155, 20, 140, 20);
        lblCancelReservation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel.add(lblCancelReservation);
        
        JLabel lblReservationId = new JLabel("Reservation ID");
        lblReservationId.setBounds(65, 69, 100, 20);
        panel.add(lblReservationId);
        
        textField = new JTextField();
        textField.setBounds(235, 65, 134, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton btnBack = new JButton("Back");
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerFunctionalities cf = new CustomerFunctionalities();
                cf.cfWindow();
            }
        });
        btnBack.setBounds(75, 191, 100, 29);
        panel.add(btnBack);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CancelReservation_1 cr1 = new CancelReservation_1();
                cr1.cr1Window();
            }
        });
        btnSearch.setBounds(275, 191, 100, 29);
        panel.add(btnSearch);
    }
}
