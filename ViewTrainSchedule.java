import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;


public class ViewTrainSchedule {

    private JFrame frame;
    private JTextField textField;
    public int trainNumber;
    private JTable table;
    public database db;
    public String sql;
    public Object[][] ss;
    
    /**
     * Launch the application.
     */
    public static void vtsWindow() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewTrainSchedule window = new ViewTrainSchedule();
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
    public ViewTrainSchedule() {
        db = new database();
        initialize();
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
        panel.setVisible(true);
        
        JLabel lblViewTrainSchedule = new JLabel("View Train Schedule");
        lblViewTrainSchedule.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblViewTrainSchedule.setForeground(Color.ORANGE);
        lblViewTrainSchedule.setBounds(150, 20, 150, 35);
        panel.add(lblViewTrainSchedule);
        
        JLabel lblNewLabel = new JLabel("Train Number");
        lblNewLabel.setBounds(70, 90, 90, 16);
        panel.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(230, 84, 135, 28);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       
        btnSearch.setBounds(90, 195, 100, 29);
        panel.add(btnSearch);
        
        drawPanel panel_1 = new drawPanel();
        panel_1.setBounds(0, 0, 450, 300);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_1.add(panel_2, BorderLayout.NORTH);
        panel_2.setPreferredSize(new Dimension(450, 60));
        panel_2.setLayout(null);
        
        JLabel lblViewTrainSchedule_1 = new JLabel("View Train Schedule");
        lblViewTrainSchedule_1.setForeground(Color.ORANGE);
        lblViewTrainSchedule_1.setBounds(150, 20, 150, 20);
        lblViewTrainSchedule_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        panel_2.add(lblViewTrainSchedule_1);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.WHITE);
        panel_3.setPreferredSize(new Dimension(450, 90));
        panel_1.add(panel_3, BorderLayout.SOUTH);
        panel_3.setLayout(null);
        
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(88, 40, 117, 29);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel_3.add(btnBack);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.WHITE);
        panel_4.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel_1.add(panel_4, BorderLayout.CENTER);
        panel_4.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        panel_4.add(scrollPane, BorderLayout.CENTER);
        

        panel_1.setVisible(false);
        
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel_1.setVisible(false);
                panel.setVisible(true);
            }
        });
        
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                sql = "select trainNum, arrivalTime, departureTime, location, name from stop where trainNum = '" 
                        + textField.getText().trim() + "'";
                try{
                   if (db.checkFunctionality(sql).equals("")){
                       JOptionPane.showMessageDialog(null, "can not find train" + textField.getText());
                   }else{
                       ss = new Object[db.viewSchedule(sql).length][4];
                       ss = db.viewSchedule(sql);
                       
                       Object[] s= {"Train Number", "Arrival Time",  "Depature Time", "Station"};
    
                       table = new JTable(ss, s); //table
                       scrollPane.setViewportView(table);
                   }
                }catch (Exception ee){
                    
                }
                panel.setVisible(false);
                panel_1.setVisible(true);
            }
        });
    }
    
    public Object[][] resize(Object[][] ss){
        Object[][] temp = new Object[ss.length * 2][ss[0].length];
        for (int i = 0; i < ss.length; i++){
            for (int j = 0; j < ss[0].length; j++){
                temp[i][j] = ss[i][j];
            }
        }
        return temp;
    }
}
