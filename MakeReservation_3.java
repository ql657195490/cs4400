import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class MakeReservation_3 {

    private JFrame frame;
    private JTable table;
    private JTextField textField;
    public static ArrayList list;
    public static String username;
    public static Object[][] s2;
    public static DefaultTableModel model;
    public static MakeReservationData mrd;
    public static String ms;
    public static database db;
    public static double totalCost;
    public static final double bagCharge = 30;
    public String[] card;

    /**
     * Launch the application.
     */
    public static void mrWindow_3() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MakeReservation_3 window = new MakeReservation_3();
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
    public MakeReservation_3() {
        db = new database();
        totalCost = 0;
        initialize();
    }
    
    public MakeReservation_3(String username){
        this.username = username;
        mrd = new MakeReservationData(false);
        s2 = mrd.getReservationData();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 950, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(950, 90));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(null);
        
        JLabel lblMakeReservation = new JLabel("Make Reservation");
        lblMakeReservation.setForeground(Color.ORANGE);
        lblMakeReservation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblMakeReservation.setBounds(410, 20, 130, 20);
        panel.add(lblMakeReservation);
        
        JLabel lblNewLabel = new JLabel("Currently Selected");
        lblNewLabel.setBounds(25, 60, 130, 20);
        panel.add(lblNewLabel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setPreferredSize(new Dimension(950,240));
        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel();// set student discount label
        try{
            if (db.checkLoginInfo("select isStudent from customer where username = '" + username + "'").equals("true")){
                lblNewLabel_1.setText("Student discount Applied");
            }else{
                lblNewLabel_1.setText("Student discount not Applied");
            }
        }catch (Exception e){}
        
        lblNewLabel_1.setBounds(25, 60, 190, 20);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblTotalCost = new JLabel("Total cost");
        lblTotalCost.setBounds(25, 100, 80, 20);
        panel_1.add(lblTotalCost);
        
        for (int i = 0; i < s2.length; i++){
            double temp = 0;
            if (Double.parseDouble(s2[i][6].toString()) > 2){
                temp = (Double.parseDouble(s2[i][6].toString()) - 2) * bagCharge;
            }
            totalCost += Double.parseDouble((String)s2[i][5]) + temp;
        }
        textField = new JTextField();// total cost textfield
        if (lblNewLabel_1.getText().equals("Student discount Applied")){
            textField.setText(String.valueOf(totalCost * 0.8));
        }else {
            textField.setText(String.valueOf(totalCost));
        }
        
        textField.setEnabled(false);
        textField.setBounds(267, 96, 145, 28);
        panel_1.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Use card");
        lblNewLabel_2.setBounds(25, 140, 61, 20);
        panel_1.add(lblNewLabel_2);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(267, 140, 80, 27);
        panel_1.add(comboBox);
        //adding card number to comboBox
        try{
            list = db.getCard("select cardNum from paymentInfo where username = '" + username + "'");
            if (list.get(0) != null){
                for (int i = 0; i < list.size(); i++){
                    comboBox.addItem(list.get(i));
                }
            }
        }catch(Exception eee){
            
        }
        
        
        JLabel lblAddCard = new JLabel("<html><u>Add card</u></html>");
        lblAddCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        lblAddCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                MakeReservation_4 mr4 = new MakeReservation_4(username);
                mr4.mr4Window();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                lblAddCard.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lblAddCard.setForeground(SystemColor.textHighlight);
            }
        });
        lblAddCard.setForeground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
        lblAddCard.setBounds(351, 142, 61, 16);
        panel_1.add(lblAddCard);
        
        
        //continue adding a new train
        JLabel lblContinueToAdding = new JLabel("<html><u>Continue adding a train</u></html>");
        lblContinueToAdding.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblContinueToAdding.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MakeReservationData mrd = new MakeReservationData(true);
                frame.dispose();
                MakeReservation mr = new MakeReservation(username);
                mr.mrWindow();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                lblContinueToAdding.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                lblContinueToAdding.setForeground(SystemColor.textHighlight);
            }
        });
        lblContinueToAdding.setForeground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
        lblContinueToAdding.setBounds(25, 180, 160, 20);
        panel_1.add(lblContinueToAdding);
        
        JButton btnBack = new JButton("Back");
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.setBounds(98, 205, 100, 29);
        panel_1.add(btnBack);
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MakeReservation_5 mr5 = new MakeReservation_5(username);
                mr5.mrWindow_5();
            }
        });
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.setBounds(362, 205, 100, 29);
        panel_1.add(btnSubmit);
        
        JButton btnSelectAll = new JButton("Select all");
        btnSelectAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < s2.length; i++){
                    model.setValueAt(true, i, 0);
                }
            }
        });
        btnSelectAll.setBounds(98, 20, 100, 29);
        panel_1.add(btnSelectAll);
        
        JButton btnRemove = new JButton("Remove");// remove button
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ms = "";
                // TODO Auto-generated method stub
                for (int i = 0; i < table.getRowCount();i++){
                    Boolean checked = Boolean.valueOf(table.getValueAt(i, 0).toString()); // get the boolean value of current row
                    String col = table.getValueAt(i, 1).toString(); // get the train number of current row
                    double tp = Double.parseDouble(table.getValueAt(i, 6).toString()); // get the price of current row
                    double tb = Double.parseDouble(table.getValueAt(i, 7).toString()); // get the number of bags
                    
                    if (checked){ // if the current is selected then add the train number remove it
                      ms = col;
                      mrd.removeReservationData(s2, ms);
                      model.removeRow(i);
                      
                      totalCost -= tp;
                      if (tb > 2){
                          totalCost -= (tb - 2) * bagCharge;
                      }
                      if (lblNewLabel_1.getText().equals("Student discount Applied")){
                          textField.setText(String.valueOf(totalCost * 0.8));
                      }else {
                          textField.setText(String.valueOf(totalCost));
                      }
                    }
                }
            }
        });
        btnRemove.setBounds(360, 20, 100, 29);
        panel_1.add(btnRemove);
        
        table = new JTable();
        JScrollPane scrollPane;
        
//        String[] s = {"<html>Train<br/>(Train number)", "<html>Time<br/>(Duration)", "Departs From", "Arrives At", "Class"
//                , "Price", "# of Baggages", "Passenger Name", "Remove"};
//        Object[][] s1 = {//user for test
//                {1,"Click" , 1, 1, 1, 1, 1, 1, "button"},
//               
//                {2, "test", 2, 2, 2, 2, 2, 2, "button"}
//                
//        };
       // table = new JTable(s1, s);
//        table.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());
//        table.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor(new JTextField()));
//        table.getColumn("button").setCellRenderer(new ButtonRenderer());
//        table.getColumn("button").setCellEditor(new ButtonEditor(new JTextField()));
//        table.getColumnModel().getColumn(1).setCellEditor(new MyRender());//设置编辑器
//        table.getColumnModel().getColumn(1).setCellRenderer(new MyRender() );
        
       
        //table.setCellEditor(new DefaultCellEditor(button1));
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(0,25 , 0,25));
        model = new DefaultTableModel(){
            
            public Class<?> getColumnClass(int column){
                switch(column){
                    case 0 :
                        return Boolean.class;
                    case 1 :
                        return String.class;
                    case 2 : 
                        return String.class;
                    case 3 :
                        return String.class;
                    case 4 : 
                        return String.class;
                    case 5 :
                        return String.class;
                    case 6 :
                        return String.class;
                    case 7 :
                        return String.class;
                        
                        default:
                             return String.class;
                  
                }
            }
         };
         
         table.setModel(model);
         model.addColumn("Select");
         model.addColumn("Train number");
         model.addColumn("Time(Duration)");
         model.addColumn("Departs From");
         model.addColumn("Arrives At");
         model.addColumn("Class");
         model.addColumn("Price");
         model.addColumn("# of Baggage");
         model.addColumn("Passenger Name");

//         for (int i = 0;  i <= list.size(); i++){
//             model.addRow(new Object[0]);
//             model.setValueAt(false, i, 0);
//             model.setValueAt(list.get(4), i, 1);
//             model.setValueAt(list.get(6), i, 2);
//             model.setValueAt(list.get(1), i, 3);
//             model.setValueAt(list.get(2), i, 4);
//             model.setValueAt(list.get(5), i, 5);
//             model.setValueAt("", i, 6);
//             model.setValueAt(list.get(7), i, 7);
//             model.setValueAt(list.get(8), i, 8);
//         }
         for (int i = 0; i < s2.length; i++){
             model.addRow(new Object[0]);
           model.setValueAt(false, i, 0);
           model.setValueAt(s2[i][0], i, 1);
           model.setValueAt(s2[i][1], i, 2);
           model.setValueAt(s2[i][2], i, 3);
           model.setValueAt(s2[i][3], i, 4);
           model.setValueAt(s2[i][4], i, 5);
           model.setValueAt(s2[i][5], i, 6);
           model.setValueAt(s2[i][6], i, 7);
           model.setValueAt(s2[i][7], i, 8);
             
         }
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
    }
}

