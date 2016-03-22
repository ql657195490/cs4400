import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
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
    public Object[][] s1;
    public rButton rbv;
    public String trainNum;
    public String tclass;
    
    //constructor
    public MakeReservation_1(ArrayList list, Object[][] s1){
        this.list = list;
        this.username = (String)list.get(0);
        this.s1 = s1;
        createParts();
        frame.setVisible(true);
    }
    
    public void createParts(){
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
      
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(450, 60));
        panel.setLayout(null);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
      
        JPanel panel_1 = new JPanel();
        panel_1.setPreferredSize(new Dimension(450, 60));
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
                rbv = new rButton();
                trainNum = rbv.getTn();
                tclass = rbv.getTclass();
                list.add(trainNum); //index 4: train number
                list.add(tclass); // index 5: class
                frame.dispose();
                MakeReservation_2 mr2 = new MakeReservation_2(list, s1);
                mr2.mrWindow_2();
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
//        System.out.println(s1.length * 2);
//        System.out.println(s1[0].length * 2);
        
      
        scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        createTable(s1, s);
//      
//      
//        String[] s = {"<html>Train<br/>(Train number)", "<html>Time<br/>(Duration)", "1st Class Price", "2nd Class Price"};
//        Object[][] s1 = {
//                {1, 1, 1, 1},
//                {1, 1, 1 ,1}
//        };
//        table = new JTable(s1, s);
//        table.setPreferredScrollableViewportSize(new Dimension(450, 280));
//        table.setFillsViewportHeight(true);
//        scrollPane.setViewportView(table);
      
     
        frame.setBounds(100, 100, 450, 400);
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
            //System.out.println(rb[count]);
            bg.add(rb[count]);
            s3[i] = s1[i][0];
            rb[count].addItemListener(new rButton(s3[i], "first"));
            count++;
            rb[count] = (JRadioButton)s1[i][3];
            rb[count].addItemListener(new rButton(s3[i], "second"));
            bg.add(rb[count]);
            //System.out.println(rb[count]);
            //s1[i][1] = rb[i];
        }
        
        dtm = new DefaultTableModel(s1, s2);
        table = new JTable(dtm){
            public void tableChanged(TableModelEvent e){
                super.tableChanged(e);
                
                repaint();
            }
        };
        
        //ButtonGroup bg = new ButtonGroup();
        
//        for (int i = 0; i< rb.length; i++){
//            if (rb[i] == null){
//                break;
//            }
//            System.out.println(rb[i]);
//            bg.add(rb[i]);
//        }
       // table.getColumn("<html>Train<br/>(Train number)").setCellRenderer(new Radiorenderer());
        //table.getColumn("<html>Train<br/>(Train number)").setCellEditor(new radioEditor(new JCheckBox()));
        table.getColumn("1st Class Price").setCellRenderer(new Radiorenderer());
        table.getColumn("1st Class Price").setCellEditor(new radioEditor(new JCheckBox()));
        table.getColumn("2nd Class Price").setCellRenderer(new Radiorenderer());
        table.getColumn("2nd Class Price").setCellEditor(new radioEditor(new JCheckBox()));

        scrollPane.setViewportView(table);
    }

//    /**
//     * Launch the application.
//     */
//    public static void mrWindow_1() {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    MakeReservation_1 window = new MakeReservation_1(list);
//                    window.frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    /**
//     * Create the application.
//     */
//    public MakeReservation_1(ArrayList list) {
//        this.username = (String)list.get(0);
//        initialize();
//    }
//
//    /**
//     * Initialize the contents of the frame.
//     */
//    private void initialize() {
//        frame = new JFrame();
//        frame.getContentPane().setBackground(Color.WHITE);
//        frame.getContentPane().setLayout(new BorderLayout(0, 0));
//        
//        JPanel panel = new JPanel();
//        panel.setBackground(Color.WHITE);
//        panel.setPreferredSize(new Dimension(450, 60));
//        panel.setLayout(null);
//        frame.getContentPane().add(panel, BorderLayout.NORTH);
//        
//        JPanel panel_1 = new JPanel();
//        panel_1.setPreferredSize(new Dimension(450, 60));
//        panel_1.setLayout(null);
//        panel_1.setBackground(Color.WHITE);
//        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
//        
//        JButton btnNewButton = new JButton("Back");
//        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        btnNewButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                frame.dispose();
//                MakeReservation mr = new MakeReservation(username);
//                mr.mrWindow();
//            }
//        });
//        btnNewButton.setBounds(80, 20, 100, 29);
//        panel_1.add(btnNewButton);
//        
//        JButton btnNewButton_1 = new JButton("Next");
//        btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        btnNewButton_1.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                frame.dispose();
//                MakeReservation_2 mr2 = new MakeReservation_2();
//                mr2.mrWindow_2();
//            }
//        });
//        btnNewButton_1.setBounds(270, 20, 100, 29);
//        panel_1.add(btnNewButton_1);
//        
//        JLabel lblSelectDeparture = new JLabel("Select Departure");
//        lblSelectDeparture.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
//
//        lblSelectDeparture.setBounds(165, 20, 120, 20);
//        panel.add(lblSelectDeparture);
//        
//        JScrollPane scrollPane = new JScrollPane();
//        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
//        
//        
//        String[] s = {"<html>Train<br/>(Train number)", "<html>Time<br/>(Duration)", "1st Class Price", "2nd Class Price"};
//        Object[][] s1 = {
//                {1, 1, 1, 1},
//                {1, 1, 1 ,1}
//        };
//        table = new JTable(s1, s);
//        table.setPreferredScrollableViewportSize(new Dimension(450, 280));
//        table.setFillsViewportHeight(true);
//        scrollPane.setViewportView(table);
//        
//       
//        frame.setBounds(100, 100, 450, 400);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
}

class rButton implements ItemListener{
    private String tn;
    private String tclass;
    
    public rButton(){
        
    }
    
    public rButton(Object ob, String tclass){
        this.tn = (String)ob;
        this.tclass = tclass;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        System.out.println(tn);
        
    }
    
    public String getTn(){
        return this.tn;
    }
    
    public String getTclass(){
        return this.tclass;
    }
    
}
