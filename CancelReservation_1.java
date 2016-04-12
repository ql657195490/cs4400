import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CancelReservation_1 {

    private JFrame frame;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    public static Object[][] s;
    public static String username;
    public database db;
    private final static int bagfee = 30;
    private final static int changefee = 50;
    private static double cost;
    private static double refund;
    private static String year;
    private static String month;
    private static String date;
    private static String cancel;
    private static Date cancelDate;
    private static Date currentDate;
    private static Calendar now;
    private static String current;
    private static int oldyear;
    private static int oldmonth;
    private static int olddate;
    private static  DateFormat df;
    private static int sevendays;
    private static int oneday;  
    public static String reservationID;
    

    /**
     * Launch the application.
     */
    public static void cr1Window() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CancelReservation_1 window = new CancelReservation_1();
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
    public CancelReservation_1() {
        db = new database();
        now = Calendar.getInstance();
        year  = String.valueOf(now.get(Calendar.YEAR)) + "-";
        month = String.valueOf((now.get(Calendar.MONTH) + 1)) + "-";
        date = String.valueOf(now.get(Calendar.DATE));
        current = "";
        cancel = year + month + date;
        df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("cancel date is " + cancel);
        cancelDate = null;
        currentDate = null;
        sevendays = 7 * 24 * 60 * 60 * 1000;// seven day represent in ms
        oneday = 1 * 24 * 60 * 60 * 1000;//one day represent in ms
        cost = 0;
        try{
            current = db.getDepatureDate("select departureDate from reserves;");
            cancelDate = df.parse(cancel);
            currentDate = df.parse(current);
            System.out.println(current);
        }catch(Exception e){}
        initialize();
    }
    
    public CancelReservation_1(String username, Object[][] s, String reservationID){
        this.username = username;
        this.s = s;
        this.reservationID = reservationID;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 850, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(850, 60));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(null);
        
        JLabel lblCancelReservation = new JLabel("Cancel Reservation");
        lblCancelReservation.setForeground(Color.ORANGE);
        lblCancelReservation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblCancelReservation.setBounds(355, 20, 140, 20);
        panel.add(lblCancelReservation);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setPreferredSize(new Dimension(850, 190));
        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(null);
        
        JLabel lblTotalCostOf = new JLabel("Total Cost of Reservation");
        lblTotalCostOf.setBounds(10, 10, 160, 20);
        panel_1.add(lblTotalCostOf);
        
        textField = new JTextField();
        textField.setBounds(260, 7, 134, 28);
        panel_1.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Date of Cancellation");
        lblNewLabel.setBounds(10, 45, 128, 20);
        
        panel_1.add(lblNewLabel);
        
        textField_1 = new JTextField();
        textField_1.setBounds(260, 41, 134, 28);
        textField_1.setText(cancel);
        textField_1.setEnabled(false);
        panel_1.add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Amount to be refund");
        lblNewLabel_1.setBounds(10, 80, 140, 20);
        panel_1.add(lblNewLabel_1);
        
        textField_2 = new JTextField();
        textField_2.setBounds(260, 80, 134, 20);
        panel_1.add(textField_2);
        textField_2.setColumns(10);
        
        JButton btnBack = new JButton("Back");
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CancelReservation cs = new CancelReservation();
                cs.crWindow();
            }
        });
        btnBack.setBounds(69, 128, 100, 29);
        panel_1.add(btnBack);
        
        JButton btnSubmit = new JButton("Submit");
        
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    db.update("UPDATE reservation SET isCanceled = 'true' WHERE reservationID = " + reservationID + ";");
                }catch(Exception ee){}
                System.exit(0);
            }
        });
        btnSubmit.setBounds(271, 128, 100, 29);
        panel_1.add(btnSubmit);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.getContentPane().add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        panel_2.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable(s, new Object[]{"<html>Train<br>(Train Number)", "<html>Time<br>(Duration)", "Departs From", "Arrives At", "Class", "Price", "# of baggages", "Passenger Name"});
        table.setEnabled(false);
        scrollPane.setViewportView(table);
        System.out.println("number of row: " + table.getRowCount());
        for (int i = 0; i < table.getRowCount(); i++){
            cost += Integer.parseInt(table.getModel().getValueAt(i, 5).toString());
            if (Integer.parseInt(table.getModel().getValueAt(i, 6).toString()) > 2){
                cost += (Integer.parseInt(table.getModel().getValueAt(i, 6).toString()) - 2) * bagfee;
            }
        }
        try{
            if (db.checkFunctionality("SELECT isStudent FROM customer WHERE username = '" + username + "';").equals("true")){
                cost *= 0.8;
            }
        }catch(Exception e){}
        textField.setText(String.valueOf(cost));
        if (currentDate.getTime() - cancelDate.getTime() > sevendays){
            System.out.println("80% refund");
            refund = cost * 0.8;
        }else if (currentDate.getTime() - cancelDate.getTime() <  sevendays && currentDate.getTime() - cancelDate.getTime() > oneday){
            System.out.println("50% refund");
            refund = cost * 0.5;
        }else{
            refund = 0;
            System.out.println("no refund");
        }
        textField_2.setText(String.valueOf(refund));
        textField_2.setEnabled(false);
        textField_1.setEnabled(false);
    }
}
