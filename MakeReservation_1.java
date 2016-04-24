import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


public class MakeReservation_1 {

    private JFrame frame;
    private JTable table;
    public DefaultTableModel dtm = new DefaultTableModel();
    public JScrollPane scrollPane;
    public static String username;
    public static ArrayList list;
    public static Object[][] s1;
    public rButton rbv;
    public static String trainNum;
    public String tclass;
    public static String time;
    public static Object[][] s2;
    public MakeReservationData mrd;
    public static int position;
    
    //constructor
    public MakeReservation_1(String username, Object[][] s1){
        this.list = list;
        this.username = username;
        this.s1 = s1;
        mrd = new MakeReservationData(false);
        s2 = mrd.getReservationData();
        createParts();
        frame.setVisible(true);
    }
    
    public void createParts(){
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
      
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(500, 60));
        panel.setLayout(null);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
      
        JPanel panel_1 = new JPanel();
        panel_1.setPreferredSize(new Dimension(500, 60));
        panel_1.setLayout(null);
        panel_1.setBackground(Color.WHITE);
        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
      
        JButton btnNewButton = new JButton("Back");
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MakeReservation mr = new MakeReservation(username);
                mr.mrWindow();
          }
        });
        btnNewButton.setBounds(80, 20, 100, 29);
        panel_1.add(btnNewButton);
     
        JButton btnNewButton_1 = new JButton("Next");
        btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < s2.length; i++){
                    if (s2[i][1] == null){
                        position = i;
                        break;
                    }
                }
                if (s2[position][0] == null){
                    JOptionPane.showMessageDialog(null, "you must select a train");
                }else{
                    s2 = mrd.getReservationData();
                    time = getTime(s1, (String)s2[position][0]);

                    time = s2[position][8].toString().substring(0, s2[position][8].toString().length() - 10) + time;
                      s2[position][1] = time; // index 1: time

                    mrd.setReservationData(s2);
                    frame.dispose();
                    MakeReservation_2 mr2 = new MakeReservation_2(username, s1);
                    mr2.mrWindow_2();
                }
                
            }
        });
        
        btnNewButton_1.setBounds(270, 20, 100, 29);
        panel_1.add(btnNewButton_1);
      
        JLabel lblSelectDeparture = new JLabel("Select Departure");
        lblSelectDeparture.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

        lblSelectDeparture.setBounds(165, 20, 120, 20);
        panel.add(lblSelectDeparture);
        String[] s = {"<html>Train<br/>(Train number)", "<html>Time<br/>(Duration)", "1st Class Price", "2nd Class Price"};       
//        Object[][] s1 = {
//              {"1", "1", new JRadioButton("1"), new JRadioButton("2")}
//             
//          };

        
      
        scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        createTable(s1, s);

      
     
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void createTable(Object[][] s1, Object[] s2){
        
        int count = 0;
        ButtonGroup bg = new ButtonGroup();
        Object[] s3 = new Object[s1.length];
        JRadioButton[] rb = new JRadioButton[s1.length * 2];


         
        
        for (int i = 0; i < s1.length; i++){
            if (s1[i][2] == null){
                break;
            }
            
            rb[count] = (JRadioButton)s1[i][2];
            bg.add(rb[count]);
            s3[i] = s1[i][0];
            rb[count].addItemListener(new rButton(s3[i], "first", rb[count].getText(), position));
            
            count++;
            
            rb[count] = (JRadioButton)s1[i][3];
            bg.add(rb[count]);
            rb[count].addItemListener(new rButton(s3[i], "second", rb[count].getText(), position));
            
            
            
           
        }
        
 
       
        
        

        table  = new JTable(s1, s2);
        
        table.getColumn("1st Class Price").setCellRenderer(new Radiorenderer());
        table.getColumn("1st Class Price").setCellEditor(new radioEditor(new JCheckBox()));
        table.getColumn("2nd Class Price").setCellRenderer(new Radiorenderer());
        table.getColumn("2nd Class Price").setCellEditor(new radioEditor(new JCheckBox()));
        
        table.setRowHeight(50);

        scrollPane.setViewportView(table);
    }
    
    
    private class rButton implements ItemListener{
        public String tn;
        public 
        String tclass;
        public String price;
        public Object[][] ss;
        public MakeReservationData mdr1;
        public int position;
        
        public rButton(){
            
        }
        
        public rButton(Object ob, String tclass, String price, int position){
            this.tn = (String)ob;
            this.tclass = tclass;
            this.price = price;
          
            mdr1 = new MakeReservationData(false);
        }
        @Override
        public void itemStateChanged(ItemEvent e) {
            // TODO Auto-generated method stub
      
            this.ss = mrd.getReservationData();
            int position = 0;
            for (int i = 0; i < ss.length; i++){
                if (ss[i][0] == null){
                    position = i;
                    
                }
            }
            ss[position][0] = tn; //index 0: train number
            //ss[position][1] = time; // index 1: time
            ss[position][4] = tclass; // index 4 : class
            ss[position][5] = price; // index 5: price
            mdr1.setReservationData(ss);
            
            
        }
        
        public String getTn(){
            return this.tn;
        }
        
        public String getTclass(){
            return this.tclass;
        }
        
        public String getPrice(){
            return this.price;
        }
    }
    
    public String getTime(Object[][] s1, String tn){
        for (int i = 0; i < s1.length; i++){

            if (s1[i][0].equals(tn)){
                this.time = (String)s1[i][1];
                break;
                
                
            }
        }
        return this.time;
    }


}


