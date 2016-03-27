import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class MakeReservation_2 {

    private JFrame frame;
    private JTextField textField;
    public static ArrayList list;
    public static String username;
    public static Object[][] s1;
    public static Object[][] s2;
    public MakeReservationData mrd;

    /**
     * Launch the application.
     */
    public static void mrWindow_2() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MakeReservation_2 window = new MakeReservation_2(username, s1);
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
    public MakeReservation_2(String username, Object[][] s1) {
        this.username = username;
        this.s1 = s1;
        mrd = new MakeReservationData(false);
        s2 = mrd.getReservationData();
        
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
        
        JLabel lblTravelExtrasAnd = new JLabel("Travel Extras and Passenger Info");
        lblTravelExtrasAnd.setForeground(Color.ORANGE);
        lblTravelExtrasAnd.setBounds(105, 20, 240, 20);
        lblTravelExtrasAnd.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel.add(lblTravelExtrasAnd);
        
        JLabel lblNumberOfBaggage = new JLabel("Number of baggage");
        lblNumberOfBaggage.setBounds(51, 64, 125, 20);
        panel.add(lblNumberOfBaggage);
        
        String[] numberBaggage = {"0", "1", "2", "3", "4"};
        JComboBox comboBox = new JComboBox(numberBaggage);
        comboBox.setEditable(true);
        comboBox.setBounds(275, 60, 52, 27);
       
        
        panel.add(comboBox);
        
        JLabel lblNewLabel = new JLabel("Every passenger can bring up to 4 baggage. 2 free of charge, 2 for $30 per bag");
        lblNewLabel.setBounds(51, 90, 364, 20);
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
        panel.add(lblNewLabel);
        
        JLabel lblPassengerName = new JLabel("Passenger Name");
        lblPassengerName.setBounds(51, 130, 105, 16);
        panel.add(lblPassengerName);
        
        textField = new JTextField();
        textField.setBounds(246, 124, 134, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton btnBack = new JButton("Back");
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
                frame.dispose();
                MakeReservation_1 mr1 = new MakeReservation_1(list, s1);
                //mr1.mrWindow_1();
            }
        });
        btnBack.setBounds(80, 305, 100, 29);
        panel.add(btnBack);
        
        JButton btnNewButton = new JButton("Next");
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "you need to enter a name");
                }
                
                //list.add(comboBox.getSelectedItem()); // index 7: number of baggage
                //list.add(textField.getText().trim()); // index 8: passenger name
                
                //add data
                int position = 0;
                for (int i = 0; i < s2.length; i++){
                    if (s2[i][6] == null){
                        position = i;
                        break;
                        
                    }
                }
                s2[position][6] = comboBox.getSelectedItem(); //index 6: number of baggage
                s2[position][7] = textField.getText().trim(); // index 7: passenger name
                mrd.setReservationData(s2);
                
                frame.dispose();
                MakeReservation_3 mr3 = new MakeReservation_3(username);
                mr3.mrWindow_3();
            }
        });
        btnNewButton.setBounds(228, 305, 100, 29);
        panel.add(btnNewButton);
    }
}
