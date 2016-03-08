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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MakeReservation {

    private JFrame frame;
    private JTextField textField;
    private boolean a, b;

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
        a = true;
        b = true;
        
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
        
        JLabel lblSearchTrain = new JLabel("Search Train"); // title label
        lblSearchTrain.setForeground(Color.ORANGE);
        lblSearchTrain.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblSearchTrain.setBounds(180, 20, 90, 20);
        panel.add(lblSearchTrain);
        
        JLabel lblDeportsFrom = new JLabel("Deports From"); // deports from label
        lblDeportsFrom.setBounds(70, 80, 90, 20);
        panel.add(lblDeportsFrom);
        
        JLabel lblArrivesAt = new JLabel("Arrives At");// arrives at label
        lblArrivesAt.setBounds(70, 120, 70, 20);
        panel.add(lblArrivesAt);
        
        JLabel lblDepatureDate = new JLabel("Depature Date"); //depature date label
        lblDepatureDate.setBounds(70, 160, 90, 20);
        panel.add(lblDepatureDate);
        
        JComboBox comboBox = new JComboBox(); // comboBox for deports from
        comboBox.setBounds(242, 78, 110, 27);
        panel.add(comboBox);
        
        JComboBox comboBox_1 = new JComboBox(); // comBox for arrives At
        comboBox_1.setBounds(242, 118, 161, 27);
        panel.add(comboBox_1);
        
        textField = new JTextField();// date
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (textField.getText().trim().length() < 2){
                    a = true;
                    b = true;
                }else  if ( textField.getText().trim().length() < 5){
                    b = true;
                }if (textField.getText().trim().length() == 2 && a){
                    if (!(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)){
                        textField.setText(textField.getText().trim() + "/");
                        a = false;
                        b = true;
                    }
                }else if (textField.getText().trim().length() == 4){
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                        textField.setText(textField.getText().trim().substring(0, 3));
                        a = true;
                    }
                }else if (textField.getText().trim().length() == 5 && b){
                    textField.setText(textField.getText().trim() + "/");
                    b = false;
                }else if (textField.getText().trim().length() == 7){
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                        textField.setText(textField.getText().trim().substring(0, 6));
                        b = true;
                    }
                }else if (textField.getText().trim().length() == 10){
                    if (!(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)){
                        
                        JOptionPane.showMessageDialog(null, "date format must be mm/dd/yyyy");
                        textField.setText(textField.getText().trim().substring(0, textField.getText().trim().length() - 1));
                    }
                }
           
            }
        });
        textField.setBounds(242, 160, 110, 20);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton btnNewButton = new JButton("New button");//calendar button
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.setBounds(364, 157, 44, 29);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Find Train");// find train button
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
