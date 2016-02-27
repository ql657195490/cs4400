import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GiveReview {

    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void grWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GiveReview window = new GiveReview();
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
    public GiveReview() {
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
        
        JLabel lblGiveReview = new JLabel("Give Review");
        lblGiveReview.setForeground(Color.ORANGE);
        lblGiveReview.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblGiveReview.setBounds(180, 20, 90, 20);
        panel.add(lblGiveReview);
        
        JLabel lblTrainNumber = new JLabel("Train Number");
        lblTrainNumber.setBounds(60, 70, 90, 20);
        panel.add(lblTrainNumber);
        
        textField = new JTextField();
        textField.setBounds(212, 66, 134, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        JLabel lblRating = new JLabel("Rating");
        lblRating.setBounds(60, 110, 61, 16);
        panel.add(lblRating);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(212, 106, 80, 27);
        panel.add(comboBox);
        
        JLabel lblComment = new JLabel("Comment");
        lblComment.setBounds(60, 150, 61, 20);
        panel.add(lblComment);
        
        JButton btnAddComment = new JButton("Add Comment");
        btnAddComment.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAddComment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAddComment.setText("Edit Comment");
                GiveComment gc = new GiveComment();
                gc.gcWindow();
            }
        });
        btnAddComment.setBounds(212, 145, 117, 29);
        panel.add(btnAddComment);
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnSubmit.setBounds(175, 220, 100, 29);
        panel.add(btnSubmit);
    }
}
