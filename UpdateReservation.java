import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UpdateReservation {

    private JFrame frame;
    private JTextField textField;
    public static String username;
    public static database db;
    public static Object[][] s;

    /**
     * Launch the application.
     */
    public static void urWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateReservation window = new UpdateReservation();
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
    public UpdateReservation() {
        
        db = new database();
        initialize();
    }
    
    public UpdateReservation(String username){
        this.username = username;
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
        
        JLabel lblUpdateReservation = new JLabel("Update Reservation");
        lblUpdateReservation.setForeground(Color.ORANGE);
        lblUpdateReservation.setBounds(155, 20, 140, 20);
        lblUpdateReservation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel.add(lblUpdateReservation);
        
        JLabel lblReservationId = new JLabel("Reservation ID");
        lblReservationId.setBounds(60, 70, 120, 16);
        panel.add(lblReservationId);
        
        textField = new JTextField();
        textField.setBounds(250, 64, 140, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "reservationID cannot be null");
                }else{
                    try{
                        String sql = "SELECT reservationID FROM reservation WHERE reservationID = '" + textField.getText().trim() + "';";
                        if (db.checkFunctionality(sql).equals(textField.getText().trim())){
                            System.out.println("test");
                            sql = "select trainNum, departureTime,"
                                    + " arrivalTime, departsFrom, arrivesAt, class, numOfBaggages, passengerName, fClassPrice, sClassPrice, departureDate"
                                    + " from ((reserves natural join(select trainNum, arrivalTime from stop where location "
                                    + "in (select arrivesAt from reserves where reservationID = " + textField.getText().trim() + " and trainNum "
                                    + "in(select trainNum from reserves where reservationID = " + textField.getText().trim() + ") )and trainNum "
                                    + "in (select trainNum from reserves where reservationID = " + textField.getText().trim() + ")) as a) natural join "
                                    + "(select trainNum, departureTime from stop where location in "
                                    + "(select departsFrom from reserves where reservationID = " + textField.getText().trim() + " and trainNum in(select trainNum from reserves where reservationID = " + textField.getText().trim() + ") )"
                                    + "and trainNum in (select trainNum from reserves where reservationID = " + textField.getText().trim() + ")) as c) natural join trainRoute where ReservationID = " + textField.getText().trim() + ";";
                            int size = db.UpdateReservationSize(sql);
                            s = db.getUpdateReservation(sql, size);
                            frame.dispose();
                            UpdateReservation_1 ur1 = new UpdateReservation_1(s
                          , new Object[]{"Select", "TrainNum", "Time", "Departs From", "Arrives At", "Class", "Price", "#of Baggages", "Passenger Name"}
                            , textField.getText().trim(), username);
                        }else{
                            JOptionPane.showMessageDialog(null, "cannnot find the reservation");
                        }
                            
                    }catch (Exception ee){}
                        
                }
                        
                //}
                
            }
        });
        btnSearch.setBounds(270, 208, 100, 29);
        panel.add(btnSearch);
        
        JButton btnBack = new JButton("Back");
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CustomerFunctionalities cf = new CustomerFunctionalities(username);
                cf.cfWindow();
            }
        });
        btnBack.setBounds(80, 208, 100, 29);
        panel.add(btnBack);
    }
}
