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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class MakeReservation {

    private JFrame frame;
    private JTextField textField;
    private boolean a, b;
    public String[] station;
    public static String username;
    public database db;
    public static ArrayList list;
    public static Object[][] s1;
    public static Object[][] s2;
    public MakeReservationData mdr;
    public MonthConverter mc;
    public static DateFormat df;
    public static long time;
    public static  boolean m;

    /**
     * Launch the application.
     */
    public static void mrWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MakeReservation window = new MakeReservation(username);
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
    public MakeReservation(String username) {
        this.username = username;
        db = new database();
        list = new ArrayList();
        mc = new MonthConverter();
        df = new SimpleDateFormat("yyyy-MM-dd");
        m = true;
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
        
        try{
            station = db.getStation("select * from station"); 
        }catch (Exception e){
            
        }
        JComboBox comboBox = new JComboBox(station); // comboBox for deports from

        comboBox.setBounds(242, 78, 161, 27);
        panel.add(comboBox);
        
        JComboBox comboBox_1 = new JComboBox(station); // comBox for arrives At
        comboBox_1.setBounds(242, 118, 161, 27);
        panel.add(comboBox_1);
        
        textField = new JTextField();// date
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyText(e.getKeyCode()).matches("[A-Za-z-+,=.]"))){
                    JOptionPane.showMessageDialog(null, "data should be numbers");
                    textField.setText(textField.getText().trim().substring(0, textField.getText().trim().length() - 1));
                }else{
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
                            textField.setText(textField.getText().trim().substring(0, 5));
                            b = true;
                        }
                    }else if (textField.getText().trim().length() == 10){
                        if (!(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)){
                            
                            JOptionPane.showMessageDialog(null, "date format must be mm/dd/yyyy");
                            textField.setText(textField.getText().trim().substring(0, textField.getText().trim().length() - 1));
                        }
                        
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
                time = 0;
                try{
                    time = df.parse(textField.getText().trim().substring(6, 10) + "-" + textField.getText().trim().substring(0, 2) + "-" + textField.getText().trim().substring(3, 5)).getTime() - df.parse(df.format(new Date())).getTime();
                    
                }catch(Exception a){}
                
                if (comboBox.getSelectedItem().equals(comboBox_1.getSelectedItem())){
                    JOptionPane.showMessageDialog(null, "departs from and arrives at station cannot be same");
                }else if(textField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "departure date cannot be null");
                }else if (textField.getText().trim().length() != 10){
                    JOptionPane.showMessageDialog(null, "date format must be mm/dd/yyyy");
                }else if(time < 0){
                    JOptionPane.showMessageDialog(null, "date not available");
                }else{
                    String n1 = "";
                    String n2 = "";
                    String n3 = "";
                    String n4 = "";
                    int count = 0;
                    for (int i = 0; i < String.valueOf(comboBox.getSelectedItem()).length(); i++){
                        if (String.valueOf(comboBox.getSelectedItem()).substring(i, i + 1).equals("(")){
                            count = i;
                            n1 = String.valueOf(comboBox.getSelectedItem()).substring(0, i);
                            n2 = String.valueOf(comboBox.getSelectedItem()).substring(i + 1, String.valueOf(comboBox.getSelectedItem()).length() - 1);
                            count = 0;
                        }
                       
                    }
                    for (int i = 0; i < String.valueOf(comboBox_1.getSelectedItem()).length(); i++){
                        if (String.valueOf(comboBox_1.getSelectedItem()).substring(i, i + 1).equals("(")){
                            count = i;
                            n3 = String.valueOf(comboBox_1.getSelectedItem()).substring(0, i);
                            n4 = String.valueOf(comboBox_1.getSelectedItem()).substring(i + 1, String.valueOf(comboBox_1.getSelectedItem()).length() - 1);

                        }
                        
                    }
                    if (Integer.parseInt(textField.getText().trim().substring(0, 2)) > 12){
                        JOptionPane.showMessageDialog(null, "month should between 01 - 12");
                    }else{
                        switch (Integer.parseInt(textField.getText().trim().substring(0, 2))){
                        case 1:
                            monthDate(1, Integer.parseInt(textField.getText().trim().substring(3,5)));
                            break;
                        case 2:
                            if( Integer.parseInt(textField.getText().trim().substring(6,10)) % 400 == 0){
                                monthDate(4, Integer.parseInt(textField.getText().trim().substring(3,5)));
                            }else if(Integer.parseInt(textField.getText().trim().substring(6,10)) % 4 == 0){
                                if (Integer.parseInt(textField.getText().trim().substring(6,10)) % 100 != 0){
                                    monthDate(4, Integer.parseInt(textField.getText().trim().substring(3,5)));
                                }else{
                                    monthDate(3, Integer.parseInt(textField.getText().trim().substring(3,5)));
                                }
                            }else{
                                monthDate(3, Integer.parseInt(textField.getText().trim().substring(3,5)));
                            }
                            
                            break;
                        case 3:
                            monthDate(1, Integer.parseInt(textField.getText().trim().substring(3,5)));
                            break;
                        case 4:
                            monthDate(2, Integer.parseInt(textField.getText().trim().substring(3,5)));
                            break;
                        case 5:
                            monthDate(1, Integer.parseInt(textField.getText().trim().substring(3,5)));
                            break;
                        case 6:
                            monthDate(2, Integer.parseInt(textField.getText().trim().substring(3,5)));
                            break;
                        case 7:
                            monthDate(1, Integer.parseInt(textField.getText().trim().substring(3,5)));
                            break;
                        case 8:
                            monthDate(1, Integer.parseInt(textField.getText().trim().substring(3,5)));
                            break;
                        case 9:
                            monthDate(2, Integer.parseInt(textField.getText().trim().substring(3,5)));
                            break;
                        case 10:
                            monthDate(1, Integer.parseInt(textField.getText().trim().substring(3,5)));
                            break;
                        case 11:
                            monthDate(2, Integer.parseInt(textField.getText().trim().substring(3,5)));
                            break;
                        case 12:
                            monthDate(1, Integer.parseInt(textField.getText().trim().substring(3,5)));
                            break;
                            
                    
                    }

//                    list.add(username);//index 0 : username
//                    list.add(comboBox.getSelectedItem()); // index 1 : departs from
//                    list.add(comboBox_1.getSelectedItem()); //index 2 : arrives at
//                    list.add(textField.getText().trim()); //index 3: departure date
                    
                    //adding data
                        if(m){
                            mdr = new MakeReservationData(false);
                            s2 = mdr.getReservationData();
                            //s2 = new Object[1][9];//user for test
                            int position = 0;
                            for (int i = 0; i < s2.length; i++){
                                if (s2[i][0] == null){
                                    position = i;
                                    break;
                                }
                            }
                            s2[position][2] = location((String)comboBox.getSelectedItem()); //index 2: departs from
                            s2[position][3] = location((String)comboBox_1.getSelectedItem()); //index 3: arrives at
                            String ymd = textField.getText().trim().substring(6, 10)
                                    + "-" + textField.getText().trim().substring(0,2) + "-" +
                                    textField.getText().trim().substring(3, 5);
                            System.out.println("ymd is " + ymd);
                            ymd = "<html>" + mc.changeMonth(textField.getText().trim()) + ymd;
                            System.out.println("ymd is " + ymd);
                            
                            
                            s2[position][8] = ymd; //index 8: date
                            mdr.setReservationData(s2);
                        
                            try{
                                s1 = db.TrainOption("select * from (select * from (select trainNum, departureTime from stop "
                                        + " where name = '" + n2 + "' and location = '" + n1 + "') as a1 natural join "
                                        +"(select trainNum, arrivalTime from stop where name = '" + n4 + "' and location = '" + n3 + "') as a2) as a3"
                                        + " natural join trainRoute");
                            }catch (Exception ee){
                              
                            }
                            
                            if(s1[0][0] == null){
                                JOptionPane.showMessageDialog(null, "No train available");
                            }else{
                                frame.dispose();
                                MakeReservation_1 mr = new MakeReservation_1(username, s1);
                            }
                        }

                        
                    }
                    
                    
                    //mr.mrWindow_1();
                }
              }
          });
        btnNewButton_1.setBounds(286, 310, 100, 29);
        panel.add(btnNewButton_1);
        
        
        
        
    }
    
    public void monthDate(int a, int b){
        if (a == 1){
            if (b > 31){
                JOptionPane.showMessageDialog(null, "date should between 01 - 31");
                this.m = false;
            }
        }else if (a == 2){
            if (b > 30){
                JOptionPane.showMessageDialog(null, "date should between 01 - 30");
                this.m = false;
            }
        }else if (a == 3){
            if (b > 28){
                JOptionPane.showMessageDialog(null, "date should between 01 - 28");
                this.m = false;
            }
        }else{
            if (b > 29){
                JOptionPane.showMessageDialog(null, "date should between 01 - 29");
                this.m = false;
            }
        }
    }
    
    private String location(String ads){
        String temp = "";
        for (int i = 0; i < ads.length() - 1; i++){
            if (ads.substring(i, i + 1).equals("(")){
                break;
            }
            temp += ads.substring(i, i + 1);
        }
        return temp;
    }
}
