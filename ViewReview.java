import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ViewReview {

    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void vrWindow(    ) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewReview window = new ViewReview();
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
    public ViewReview() {
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
        
        JLabel lblViewReview = new JLabel("View Review");
        lblViewReview.setForeground(Color.ORANGE);
        lblViewReview.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblViewReview.setBounds(180, 20, 90, 20);
        panel.add(lblViewReview);
        
        JLabel lblTrainNumber = new JLabel("Train Number");
        lblTrainNumber.setBounds(60, 75, 90, 20);
        panel.add(lblTrainNumber);
        
        textField = new JTextField();
        textField.setBounds(256, 70, 134, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerFunctionalities cf = new CustomerFunctionalities();
                cf.cfWindow();
            }
        });
        btnNewButton.setBounds(100, 228, 100, 29);
        panel.add(btnNewButton);
        
        JButton btnNext = new JButton("Next");
        btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ViewReview_1 vr1 = new ViewReview_1();
                vr1.vr1Window();
            }
        });
        btnNext.setBounds(230, 228, 100, 29);
        panel.add(btnNext);
    }
}
