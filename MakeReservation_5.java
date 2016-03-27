import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MakeReservation_5 {

    private JFrame frame;
    public static String username;

    /**
     * Launch the application.
     */
    public static void mrWindow_5() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MakeReservation_5 window = new MakeReservation_5();
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
    public MakeReservation_5() {
        initialize();
    }
    
    public MakeReservation_5(String username){
        this.username = username;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 450, 400);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Confirmation");
        lblNewLabel.setForeground(Color.ORANGE);
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel.setBounds(175, 20, 100, 20);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Reservation ID");
        lblNewLabel_1.setBounds(40, 60, 100, 20);
        panel.add(lblNewLabel_1);
        
        JLabel label = new JLabel("");
        label.setBounds(234, 62, 61, 16);
        panel.add(label);
        
        JLabel lblNewLabel_2 = new JLabel("Thank you for your purchase! Please save reservation ID for your records");
        lblNewLabel_2.setBounds(40, 90, 350, 20);
        lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
        panel.add(lblNewLabel_2);
        
        JButton btnNewButton = new JButton("Go back to choose funtionality");
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerFunctionalities cf = new CustomerFunctionalities(username);
                cf.cfWindow();
            }
        });
        btnNewButton.setBounds(115, 218, 220, 29);
        panel.add(btnNewButton);
    }

}
