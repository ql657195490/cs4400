import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MakeReservation_4 {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    public static String username;
    public static database db;

    /**
     * Launch the application.
     */
    public static void mr4Window() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MakeReservation_4 window = new MakeReservation_4();
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
    public MakeReservation_4() {
        db = new database();
        initialize();
    }
    
    public MakeReservation_4(String username){
        this.username = username;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(450, 60));
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(null);
        
        JLabel lblPaymentInformation = new JLabel("Payment information");
        lblPaymentInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblPaymentInformation.setForeground(Color.ORANGE);
        lblPaymentInformation.setBounds(155, 20, 140, 20);
        panel.add(lblPaymentInformation);
        
        JSplitPane splitPane = new JSplitPane();
        frame.getContentPane().add(splitPane, BorderLayout.CENTER);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setPreferredSize(new Dimension(220, 340));
        splitPane.setLeftComponent(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblAddCard = new JLabel("Add Card");
        lblAddCard.setBounds(80, 0, 60, 20);
        panel_1.add(lblAddCard);
        
        JLabel lblNameOnCard = new JLabel("Name on Card");
        lblNameOnCard.setBounds(60, 30, 100, 20);
        panel_1.add(lblNameOnCard);
        
        textField = new JTextField();
        textField.setBounds(40, 50, 140, 28);
        panel_1.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Card number");
        lblNewLabel.setBounds(65, 85, 90, 16);
        panel_1.add(lblNewLabel);
        
        textField_1 = new JTextField();
       
        textField_1.setBounds(40, 105, 140, 28);
        panel_1.add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("CVV");
        lblNewLabel_1.setBounds(95, 140, 30, 20);
        panel_1.add(lblNewLabel_1);
        
        textField_2 = new JTextField();
        textField_2.setBounds(40, 160, 140, 28);
        panel_1.add(textField_2);
        textField_2.setColumns(10);
        
        
        JLabel lblNewLabel_2 = new JLabel("Expiration date");
        lblNewLabel_2.setBounds(60, 200, 100, 16);
        panel_1.add(lblNewLabel_2);
        
        textField_3 = new JTextField();
        textField_3.setBounds(40, 220, 70, 28);
        panel_1.add(textField_3);
        textField_3.setColumns(10);
        //set expiration date format
        textField_3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (textField_3.getText().matches("[A-Za-z]+")){
                    textField_3.setText(textField_3.getText().trim().substring(0, textField_3.getText().trim().length() - 1));
                    JOptionPane.showMessageDialog(null, "expiration date should be numbers");
                }
                if (textField_3.getText().trim().length() == 2 && e.getKeyCode() != KeyEvent.VK_BACK_SPACE){
                    
                    textField_3.setText(textField_3.getText().trim().substring(0, 2) + "/");
                }
                if (textField_3.getText().trim().length() == 4 && e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                    
                    textField_3.setText(textField_3.getText().trim().substring(0, 2));
                }
            }
        });
       
        
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.setBounds(120, 220, 70, 29);
        panel_1.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Submit");
        btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if (textField.getText().trim().equals("")){
                   JOptionPane.showMessageDialog(null, "you must to enter a name on card");
               }else if (textField_1.getText().trim().equals("")){
                   JOptionPane.showMessageDialog(null, "the card number cannot be null");
                  
               }else if (textField_2.getText().trim().equals("")){
                   JOptionPane.showMessageDialog(null, "the cvv cannot be null");
               }else if (textField_3.getText().trim().equals("")){
                   JOptionPane.showMessageDialog(null, "the expiration date cannot be null");
               }else if (textField_1.getText().trim().length() != 16){
                   JOptionPane.showMessageDialog(null, "the card number should be 16 digit");
               }else if (textField_2.getText().trim().length() != 3){
                   JOptionPane.showMessageDialog(null, "the cvv should be the last three number on the back of your card");
               }else if(textField_3.getText().trim().length() != 7){
                   textField_3.setText(textField_3.getText().trim().substring(0, textField_3.getText().trim().length() - 1));
                   JOptionPane.showMessageDialog(null, "the expiration date format is MM/YYYY");
               }else {
                   try{
                       String sql = "insert paymentInfo values(" + textField_1.getText().trim() 
                               + ", " + textField_2.getText().trim() + ", '"
                               + textField_3.getText().trim().substring(3, 7)
                               + "-" + textField_3.getText().trim().substring(0, 2) + "-" + "01', '"
                               + textField.getText().trim() + "', '" + username + "');";
                       System.out.println(sql);
                       db.update(sql);       
                       frame.dispose();
                   }catch (Exception ee){}
               }
               
               
            }
            
        });
        btnNewButton_1.setBounds(60, 265, 100, 29);
        panel_1.add(btnNewButton_1);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        splitPane.setRightComponent(panel_2);
        panel_2.setLayout(null);
        
        JLabel lblDeleteCard = new JLabel("Delete Card");
        lblDeleteCard.setBounds(70, 0, 80, 20);
        panel_2.add(lblDeleteCard);
        
        JLabel lblCardNumber = new JLabel("Card number");
        lblCardNumber.setBounds(20, 52, 90, 20);
        panel_2.add(lblCardNumber);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(109, 50, 89, 27);
        panel_2.add(comboBox);
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnSubmit.setBounds(60, 265, 100, 29);
        panel_2.add(btnSubmit);
    }

}
