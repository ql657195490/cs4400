import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.awt.GridLayout;


public class MakeReservation {

    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void mrWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MakeReservation window = new MakeReservation();
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
    public MakeReservation() {
        initialize();
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
        
        panel.setVisible(true);
        
        JLabel lblSearchTrain = new JLabel("Search Train");
        lblSearchTrain.setForeground(Color.ORANGE);
        lblSearchTrain.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblSearchTrain.setBounds(180, 20, 90, 20);
        panel.add(lblSearchTrain);
        
        JLabel lblDeportsFrom = new JLabel("Deports From");
        lblDeportsFrom.setBounds(70, 80, 90, 20);
        panel.add(lblDeportsFrom);
        
        JLabel lblArrivesAt = new JLabel("Arrives At");
        lblArrivesAt.setBounds(70, 120, 70, 20);
        panel.add(lblArrivesAt);
        
        JLabel lblDepatureDate = new JLabel("Depature Date");
        lblDepatureDate.setBounds(70, 160, 90, 20);
        panel.add(lblDepatureDate);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(242, 78, 110, 27);
        panel.add(comboBox);
        
        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setBounds(242, 118, 161, 27);
        panel.add(comboBox_1);
        
        textField = new JTextField();
        textField.setBounds(242, 160, 110, 20);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.setBounds(364, 157, 44, 29);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Find Train");
        btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              frame.dispose();
              MakeReservation_1 mr = new MakeReservation_1();
              mr.mrWindow_1();
            }
        });
        btnNewButton_1.setBounds(286, 310, 100, 29);
        panel.add(btnNewButton_1);
        
        
        
        
    }
}
