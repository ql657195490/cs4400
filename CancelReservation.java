import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CancelReservation {

    private JFrame frame;
    private JTextField textField;
    public static String username;
    public database db;
    public static Object[][] s;
    
    

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
        db = new database();
        initialize();
    }
    
    public CancelReservation(String username){
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
                CustomerFunctionalities cf = new CustomerFunctionalities(username);
                cf.cfWindow();
            }
        });
        btnBack.setBounds(75, 191, 100, 29);
        panel.add(btnBack);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "reservation ID cannot be null");
                }else{
                    try{
                       if (db.checkFunctionality("SELECT isCanceled FROM reservation WHERE reservationID = " + textField.getText().trim() + ";").equals("false")
                               && textField.getText().trim().equals(db.checkFunctionality("SELECT reservationID FROM reservation WHERE reservationID = " + textField.getText().trim() + " AND username = '" + username + "';"))){
                          
                               String  sql = "select trainNum, departureTime,"
                                       + " arrivalTime, dFrom, aAt, class, numOfBaggages, passengerName, fClassPrice, sClassPrice, departureDate"
                                       +" FROM (SELECT trainNum, location as dFrom , departureTime FROM stop "
                                       + "NATURAL JOIN (SELECT trainNum, departsFrom FROM reserves WHERE reservationID =" + textField.getText().trim() + ") AS a WHERE stop.location = a.departsFrom) as t1 "
                                       + "NATURAL JOIN(SELECT trainNum, location as aAt, arrivalTime From stop NATURAL JOIN (select trainNum, arrivesAt FROM reserves where reservationID =  "+ textField.getText().trim() + ") as aa WHERE stop.location = aa.arrivesAt) as t2 "
                                       + "NATURAL JOIN reservation NATURAL JOIN trainRoute NATURAL JOIN reserves WHERE username = '" + username + "' AND reservationID = " + textField.getText().trim() + ";";
                               s = new Object[db.UpdateReservationSize(sql)][8];
                               Object[][] ss = db.getUpdateReservation(sql, db.UpdateReservationSize(sql));
                               for (int i = 0; i < ss.length; i++){
                                   for (int j = 1; j < ss[0].length; j++){
                                       s[i][j - 1] = ss[i][j];
                                   }
                               }
                               frame.dispose();
                               CancelReservation_1 cr1 = new CancelReservation_1(username, s, textField.getText().trim());
                               cr1.cr1Window();

                       }else {
                           JOptionPane.showMessageDialog(null, "no reservation found");
                       }
                    }catch (Exception ee){
                        JOptionPane.showMessageDialog(null, "please enter correct reservation number");
                    }
                   
                }
            }
        });
        btnSearch.setBounds(275, 191, 100, 29);
        panel.add(btnSearch);
    }
}
