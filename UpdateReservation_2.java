import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UpdateReservation_2 {

    private JFrame frame;
    private JTextField textField;
    private JTable table;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTable table_1;
    private JTable table_2;
    private DefaultTableModel model;
    public static String username;
    public static String trainNum;
    public static String reservationID;
    public static Object[][] s;
    public static Object[][] s1;
    public database db;
    public UpdateReservationData urd;
    public MonthConverter mc;
    //public static float totalPrice;
    public static String totalPrice;
    public static DateFormat df;
    public static long time;

    /**
     * Launch the application.
     */
    public static void ur2Window() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateReservation_2 window = new UpdateReservation_2();
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
    public UpdateReservation_2() {
        db = new database();
        mc = new MonthConverter();
//        totalPrice = db.totalPrice;
//        System.out.println("total price is " + db.totalPrice);
        totalPrice = "";
        try{
            totalPrice = db.checkFunctionality("SELECT totalCost FROM reservation WHERE reservationID = " + reservationID + ";");
            System.out.println("total price is " + totalPrice);
        }catch(Exception e){}
        df = new SimpleDateFormat("yyyy-MM-dd");
       // this.s1 = this.s;
        initialize();
    }
    
    public UpdateReservation_2(String username, String reservationID, String trainNum, Object[][] s, Object[][] s1){
        this.username = username;
        System.out.println("2: " + this.username);
        this.reservationID = reservationID;
        this.trainNum = trainNum;
        this.s = s;
        
        this.s1 = s1;
        //urd = new UpdateReservationData();
        //this.s = urd.currentSelectData();
        //this.s1 = urd.currentSelectData();
//        System.out.println(s1[0][0]);
//        db = new database();
//        mc = new MonthConverter();
       // ur2Window();
//        urd = new UpdateReservationData();
//        this.s1 = urd.currentSelectData();
//        System.out.println("s1[0][1] is " + s1[0][1]);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 850, 580);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(850, 370));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.WHITE);
        panel_3.setPreferredSize(new Dimension(850, 70));
        panel.add(panel_3, BorderLayout.NORTH);
        panel_3.setLayout(null);
        
        JLabel lblUpdateReservation = new JLabel("Update Reservation");
        lblUpdateReservation.setForeground(Color.ORANGE);
        lblUpdateReservation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblUpdateReservation.setBounds(355, 20, 140, 20);
        panel_3.add(lblUpdateReservation);
        
        JLabel lblCurrentTicket = new JLabel("Current Train Ticket");
        lblCurrentTicket.setBounds(10, 48, 140, 20);
        panel_3.add(lblCurrentTicket);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.WHITE);
        panel_4.setPreferredSize(new Dimension(850, 40));
        panel.add(panel_4, BorderLayout.SOUTH);
        panel_4.setLayout(null);
        
        JLabel lblNew = new JLabel("New Departure Date");
        lblNew.setBounds(10, 10, 140, 20);
        panel_4.add(lblNew);
        
        textField = new JTextField();

        textField.addKeyListener(new KeyAdapter() {
            boolean a = false;
            boolean b = false;
            @Override
            public void keyPressed(KeyEvent e) {
               
               
                if (textField.getText().matches("[A-Za-z]")){
                    textField.setText("");
                    JOptionPane.showMessageDialog(null, "date should be numbers");
                }
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
        textField.setBounds(180, 6, 134, 28);
        panel_4.add(textField);
        textField.setColumns(10);
        
        JButton btnCalender = new JButton("calendar");
        btnCalender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnCalender.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCalender.setBounds(337, 7, 80, 29);
        panel_4.add(btnCalender);
        
        JButton btnSearch = new JButton("Search availability");// click this to check availability and also act the update result
        
        btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSearch.setBounds(500, 7, 140, 29);
        
        panel_4.add(btnSearch);
        
        JPanel panel_5 = new JPanel();
        panel_5.setBackground(Color.WHITE);
        panel.add(panel_5, BorderLayout.CENTER);
        panel_5.setLayout(new BorderLayout(0, 0));
        panel_5.setBorder(new EmptyBorder(10,10,10,10));
        
        JScrollPane scrollPane = new JScrollPane();
        panel_5.add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable(s
                            , new Object[]{"<html>Train<br>(Train Number)", "<html>Time<br>(Duration)", "Departs From", "Arrives At", "Class", "Price", "# of baggages", "Passenger Name"});
        table.setEnabled(false);
        scrollPane.setViewportView(table);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setPreferredSize(new Dimension(850, 330));
        frame.getContentPane().add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_6 = new JPanel();
        panel_6.setBackground(Color.WHITE);
        panel_6.setPreferredSize(new Dimension(850, 20));
        panel_2.add(panel_6, BorderLayout.NORTH);
        panel_6.setLayout(null);
//        
//        JLabel lblNewLabel = new JLabel("Update Train Ticket");
//        lblNewLabel.setBounds(10, 0, 140, 20);
//        panel_6.add(lblNewLabel);
        
        JPanel panel_7 = new JPanel();
        panel_7.setBackground(Color.WHITE);
        panel_7.setPreferredSize(new Dimension(850, 150));
        panel_2.add(panel_7, BorderLayout.SOUTH);
        panel_7.setLayout(null);
        
        JLabel lblChangeFee = new JLabel("Change Fee");
        lblChangeFee.setBounds(10, 10, 80, 20);
        panel_7.add(lblChangeFee);
        
        textField_1 = new JTextField();
        textField_1.setBounds(170, 6, 134, 28);
        panel_7.add(textField_1);
        textField_1.setColumns(10);
        textField_1.setEnabled(false);
        
        JLabel lblUpload = new JLabel("Updated Total Cost");
        lblUpload.setBounds(10, 40, 130, 20);
        panel_7.add(lblUpload);
        
        textField_2 = new JTextField();
        textField_2.setBounds(170, 36, 134, 28);
        panel_7.add(textField_2);
        textField_2.setColumns(10);
        textField_2.setEnabled(false);
        
        JButton btnBack = new JButton("Back");
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.setBounds(55, 95, 100, 29);
        btnBack.addActionListener(new ActionListener(){
            //back to previous screen
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                frame.dispose();
                UpdateReservation_1 urdata = new UpdateReservation_1();
                UpdateReservation_1 ur1 = new UpdateReservation_1(urdata.getData(),
                        new Object[]{"Select", "TrainNum", "Time", "Departs From", "Arrives At", "Class", "Price", "#of Baggages", "Passenger Name"}
                , reservationID, username);
                
            }
            
        });
        panel_7.add(btnBack);
        
        JButton btnSumbit = new JButton("Submit");
        btnSumbit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSumbit.setBounds(238, 95, 100, 29);
        btnSumbit.setEnabled(false);
        btnSumbit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                // TODO Auto-generated method stub
                
                if (textField.getText().trim().length() != 10){
                    JOptionPane.showMessageDialog(null, "date format must be mm/dd/yyyy");
                    textField.setText(textField.getText().trim().substring(0, textField.getText().trim().length() - 1));
      
                }else{
                    int check1 = JOptionPane.showConfirmDialog(null,"Are you sure to update your reservation?", "Confirm",JOptionPane.YES_NO_OPTION);
                    //need to update data to database
                    if (check1 == JOptionPane.YES_OPTION){
                        try{
                            db.update("UPDATE reserves SET departureDate = '" + textField.getText().trim().substring(6, 10) + "-" + 
                                    textField.getText().trim().substring(0, 2) + "-" +  textField.getText().trim().substring(3, 5 ) 
                                    + "' " + "WHERE reservationID = "
                                    + reservationID + " AND trainNum = '" + trainNum + "';");
                            db.update("UPDATE reservation SET isUpdated = 'true' WHERE reservationID = " + reservationID + ";");
                            db.update("UPDATE reservation SET TotalCost = " + textField_2.getText().trim() + " WHERE reservationID = " + reservationID + ";");
                        }catch(Exception ee){}
                        
                    }
                }
                
                    
                
                
            }
            
        });
        panel_7.add(btnSumbit);
        
        
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.println(s1.length);
//                urd.setUpdateReservationData(s1);\
                time = 0;
                try{
                    time = df.parse(textField.getText().trim().substring(6, 10) + "-" + textField.getText().trim().substring(0, 2) + "-" + textField.getText().trim().substring(3, 5)).getTime() - df.parse(df.format(new Date())).getTime();
                    
                }catch(Exception a){}
                
                if (textField.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "date cannot be null");
                }else if (time < 0){
                    JOptionPane.showMessageDialog(null, "data is not available");
                }else{
               
                   int choice = JOptionPane.showConfirmDialog(null, "date is available, Do you want to update your reservation?", "Confirm",JOptionPane.YES_NO_OPTION); 
                   if (choice == JOptionPane.YES_OPTION){
                       //System.out.println("s1[0][1] is " + mc.changeMonth(textField.getText().trim()) + s[0][1].toString().substring(s[0][1].toString().length() - 17, s[0][1].toString().length()));
                       //System.out.println(s[0][1].toString().length());
                      //s1[0][1] = mc.changeMonth(textField.getText().trim()) + s[0][1].toString().substring(s[0][1].toString().length() - 17, s[0][1].toString().length());
                       //urd.renewReservationData();
                      // s1 = urd.currentSelectData();
                      
                      table.getModel().setValueAt(mc.changeMonth(textField.getText().trim()) + s[0][1].toString().substring(s[0][1].toString().length() - 17, s[0][1].toString().length()), 0, 1);
                      textField_1.setText("50");
                      btnSumbit.setEnabled(true);
                      try{
                         // if (db.checkFunctionality("SELECT isStudent FROM customer WHERE username = '" + username + "';").equals("true")){
                              //totalPrice *= 0.8;
                              if (db.checkFunctionality("SELECT isUpdated FROM reservation WHERE reservationID = " + reservationID + ";").equals("false")){
                                  totalPrice += 50;
                              }
                         // }
                      }catch(Exception ee){}
                      textField_2.setText(totalPrice);
                      lblCurrentTicket.setText("Update train ticket");
                      System.out.println("s[0][1]" + s[0][1]);
                   }
                }
                
            }
        });
        table.setRowHeight(50);
        table.getColumn("<html>Time<br>(Duration)").setMinWidth(150);
        table.getColumn("Price").setMaxWidth(50);
//        JPanel panel_8 = new JPanel();
//        panel_8.setBackground(Color.WHITE);
//        panel_8.setBorder(new EmptyBorder(10, 10, 10 ,10));
//        panel_2.add(panel_8, BorderLayout.CENTER);
//        panel_8.setLayout(new BorderLayout(0, 0));
//        
//        JScrollPane scrollPane_1 = new JScrollPane();
//        panel_8.add(scrollPane_1, BorderLayout.CENTER);
      //  model = new DefaultTableModel();
//        table_2.setModel(model);
//        String[] col = {"<html>Train<br>(Train Number)", "<html>Time<br>(Duration)", "Departs From", "Arrives At", "Class", "Price", "# of baggages", "Passenger Name"};
//        for (int i = 0; i < col.length; i++){
//            model.addColumn(col[i]);
//        }
//        for (int i = 0 ; i < s.length;i++){
//            
//        }
      

//        table_2 = new JTable(s1,
//                
//                new Object[]{"<html>Train<br>(Train Number)", "<html>Time<br>(Duration)", "Departs From", "Arrives At", "Class", "Price", "# of baggages", "Passenger Name"});
//                    table_2.setEnabled(false);
//                 
//          scrollPane_1.setViewportView(table_2);
          
          //frame.setVisible(true);
    }
}
