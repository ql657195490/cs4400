import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollBar;


public class ViewTrainSchedule {

    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void vtsWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewTrainSchedule window = new ViewTrainSchedule();
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
    public ViewTrainSchedule() {
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
        panel.setVisible(true);
        
        JLabel lblViewTrainSchedule = new JLabel("View Train Schedule");
        lblViewTrainSchedule.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblViewTrainSchedule.setForeground(Color.ORANGE);
        lblViewTrainSchedule.setBounds(150, 20, 150, 35);
        panel.add(lblViewTrainSchedule);
        
        JLabel lblNewLabel = new JLabel("Train Number");
        lblNewLabel.setBounds(70, 90, 90, 16);
        panel.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(230, 84, 135, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton btnSearch = new JButton("Search");
       
        btnSearch.setBounds(90, 195, 100, 29);
        panel.add(btnSearch);
        
        drawPanel panel_1 = new drawPanel();
        panel_1.setBounds(0, 0, 450, 300);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        
        JLabel lblNewLabel_1 = new JLabel("View Train Schedule");
        lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel_1.setForeground(Color.ORANGE);
        lblNewLabel_1.setBounds(150, 20, 150, 35);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("<html>Train<br/>(Train Number)");
        lblNewLabel_2.setBounds(15, 60, 105, 35);
        panel_1.add(lblNewLabel_2);
        
        JLabel lblNewLabel_4 = new JLabel("Departure Time");
        lblNewLabel_4.setBounds(225, 60, 105, 35);
        panel_1.add(lblNewLabel_4);
        
        JLabel lblNewLabel_3 = new JLabel("Arrival Time");
        lblNewLabel_3.setBounds(120, 60, 105, 35);
        panel_1.add(lblNewLabel_3);
        
        JLabel lblNewLabel_5 = new JLabel("Station");
        lblNewLabel_5.setBounds(330, 60, 105, 35);
        panel_1.add(lblNewLabel_5);
        
        JScrollBar jsb = new JScrollBar();
        jsb.setOrientation(JScrollBar.VERTICAL);
        lblNewLabel_5.add(jsb);
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel_1.setVisible(false);
                panel.setVisible(true);
            }
        });
        btnNewButton.setBounds(100, 200, 100, 29);
        panel_1.add(btnNewButton);
        panel_1.setVisible(false);
        
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                panel_1.setVisible(true);
            }
        });
    }
}
